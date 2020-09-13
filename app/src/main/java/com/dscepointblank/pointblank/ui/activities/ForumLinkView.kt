package com.dscepointblank.pointblank.ui.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dscepointblank.pointblank.R
import kotlinx.android.synthetic.main.activity_fourm_link_view.*

class ForumLinkView : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var defaultAgent: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourm_link_view)
        window.navigationBarColor = Color.parseColor("#212121")

        val intent = intent!!
        val action = intent.action!!

        webView = forum_fragwebView
        progressBar = forum_fragprogressBar
        swipeRefreshLayout = forum_fragswipe

        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN)
        swipeRefreshLayout.setOnRefreshListener { webView.reload() }

        defaultAgent = webView.settings.userAgentString

        initWebView()
        setUpWebClients()
        loadUrl(intent.dataString!!)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {

        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)

        webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
            databaseEnabled = true
        }
    }

    private fun setUpWebClients() {

        webView.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler!!.proceed()
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                if (url!!.contains("accounts.google")) {
                    view!!.settings.userAgentString = System.getProperty("http.agent")
                } else
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
                if (newProgress == 100) {
                    progressBar.visibility = ProgressBar.GONE
                    swipeRefreshLayout.isRefreshing = false
                } else
                    progressBar.visibility = View.VISIBLE
                progressBar.progress = newProgress
            }
        }
    }

    private fun loadUrl(pageUrl: String) {
        webView.loadUrl(pageUrl)
    }

    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else
            super.onBackPressed()
    }
}