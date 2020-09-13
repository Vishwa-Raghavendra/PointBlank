package com.dscepointblank.pointblank.ui.fragments


import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.utilityClasses.Constants
import kotlinx.android.synthetic.main.fragment_webview.view.*


class WebViewFrag : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            WebViewFrag().apply {
                arguments = Bundle().apply {
                    putString(Constants.LINK, url)
                }
            }
    }

    private lateinit var pageUrl: String
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var defaultAgent :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            pageUrl = it.getString(Constants.LINK).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.isFocusableInTouchMode = true
        view.requestFocus()

        webView = view.fragwebView
        progressBar = view.fragprogressBar
        swipeRefreshLayout = view.fragswipe
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN)
        swipeRefreshLayout.setOnRefreshListener { webView.reload() }


        defaultAgent = webView.settings.userAgentString


        initWebView()
        setUpWebClients()
        loadUrl(pageUrl)


        val onBackPressed = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (webView.canGoBack())
                    webView.goBack()
                else if (activity != null)
                    requireActivity().onBackPressed()
            }

        }

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            onBackPressed,
            IntentFilter(this.hashCode().toString())
        )
    }


    private fun setUpWebClients() {

        webView.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler!!.proceed()
            }
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                if (url!!.contains("accounts.google"))
                {
                    view!!.settings.userAgentString = System.getProperty("http.agent")
                }
                else
                    view!!.settings.userAgentString = defaultAgent

                view.loadUrl(url)
                return true
            }
        }

        webView.run {
            webView.viewTreeObserver.addOnScrollChangedListener {
                swipeRefreshLayout.isEnabled = webView.scrollY == 0
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
                if (newProgress == 100) {
                    progressBar.visibility = ProgressBar.GONE
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {

        webView.setLayerType(View.LAYER_TYPE_HARDWARE,null)

        webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
            databaseEnabled = true
        }
    }

    private fun loadUrl(pageUrl: String) {
        webView.loadUrl(pageUrl)
    }
}