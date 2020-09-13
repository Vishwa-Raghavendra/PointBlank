package com.dscepointblank.pointblank.ui.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.ui.fragments.HomeScreenFragment
import com.dscepointblank.pointblank.ui.fragments.WebViewFrag
import com.dscepointblank.pointblank.utilityClasses.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener {
    private var forumWebView: Fragment? = null
    private var writeupWebView: Fragment? = null
    private lateinit var homeFrag: Fragment
    private val fm = supportFragmentManager
    private lateinit var visibleWebView: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.navigationBarColor = Color.parseColor("#212121")

        val intent = intent!!

        val isView = intent.getBooleanExtra(Constants.IS_VIEW,false)

        homeFrag = HomeScreenFragment()
        visibleWebView = homeFrag
        fm.beginTransaction().add(R.id.fragContainer, homeFrag).show(homeFrag).commit()

        bottomNavigation.setOnNavigationItemSelectedListener(this)
        fab.setOnClickListener(this)

        if(isView)
        {
            loadDSCEFrag(intent.getStringExtra(Constants.LINK_VIEW)!!)
        }

        //FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && visibleWebView !is HomeScreenFragment) {
            val intent = Intent(visibleWebView.hashCode().toString())
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            true
        } else
            super.onKeyDown(keyCode, event)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (visibleWebView is HomeScreenFragment) {
            homeFrag.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    private fun loadDSCEFrag(pageURL:String) :Boolean
    {
        if (forumWebView == null) {
            forumWebView = WebViewFrag.newInstance(pageURL)
            fm.beginTransaction().add(R.id.fragContainer, forumWebView!!)
                .hide(forumWebView!!)
                .commit()
        }

        fab.setImageResource(R.drawable.home)
        fm.beginTransaction().hide(visibleWebView).show(forumWebView!!).commit()
        visibleWebView = forumWebView!!
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuForm -> {
                return loadDSCEFrag("https://forum.dsce.in/")
            }
            R.id.menuWrite -> {

                if (writeupWebView == null) {
                    writeupWebView = WebViewFrag.newInstance("https://writeups.dsce.in/")
                    fm.beginTransaction().add(R.id.fragContainer, writeupWebView!!)
                        .hide(writeupWebView!!).commit()
                }

                fab.setImageResource(R.drawable.home)
                fm.beginTransaction().hide(visibleWebView).show(writeupWebView!!).commit()
                visibleWebView = writeupWebView!!
                return true
            }
        }
        return false
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fab -> {
                if (visibleWebView !is HomeScreenFragment) {
                    fab.setImageResource(R.drawable.ic_baseline_add_24)
                    fm.beginTransaction().hide(visibleWebView).show(homeFrag).commit()
                    visibleWebView = homeFrag
                }
            }
        }
    }
}


//    private fun checkForUpdates() =
//        try {
//            downloadController =
//                DownloadController(
//                    this@MainActivity,
//                    UpdateModel(1, "d")
//                )
//            downloadController.beginDownloadProcess()
//        }
//        catch (e :Exception)
//        {
//            Log.d("DDDD",e.localizedMessage!!)
//        }
//
//
//    private fun getCodeForces(userId: String) = GlobalScope.launch(Dispatchers.IO) {
//        try {
//            val userData = RetrofitInstance.codeForcesAPI.getUserDetails(userId)
//            withContext(Dispatchers.Main)
//            {
//                Toast.makeText(this@MainActivity, userData.details[0].firstName, Toast.LENGTH_SHORT)
//                    .show();
//            }
//
//        } catch (e: Exception) {
//            Log.d("NNNN", "error" + e.localizedMessage!!)
//        }
//    }
//
//
//    private fun sendNotification(notification: PushNotification) =
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val response = RetrofitInstance.api.postNotification(notification)
//                if (response.isSuccessful) {
//                    Log.d("DDDD", "Response is ${Gson().toJson(response.toString())}")
//                }
//
//            } catch (e: Exception) {
//                Log.d("DDDD", e.localizedMessage!!)
//            }
//        }

//
//        button.setOnClickListener {
//            if (notititle.text.toString().isNotEmpty() && notides.text.toString().isNotEmpty()) {
//
//                PushNotification(
//                    NotificationData(
//                        notititle.text.toString(),
//                        notides.text.toString()
//                    ), TOPIC
//                )
//                    .also { sendNotification(it) }
//            }
//        }
//
//        codeForces.setOnClickListener {
//            if(codeIdTV.text.toString().isNotEmpty())
//                getCodeForces(codeIdTV.text.toString())
//        }
//
//        update.setOnClickListener { checkForUpdates() }
//        fab.setBackgroundResource(R.drawable.box)

//    private val mOnNavigationItemSelectedListener =
//        BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.menuForm -> {
//                    fab.setImageResource(R.drawable.home)
//                    fm.beginTransaction().hide(visibleWebView).show(forumWebView).commit()
//                    visibleWebView = forumWebView
//                    fab.setOnClickListener {
//                        fab.setImageResource(R.drawable.ic_baseline_add_24)
//                        fm.beginTransaction().hide(visibleWebView).show(homeFrag).commit()
//                        visibleWebView = homeFrag
//                    }
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.menuWrite -> {
//                    fab.setImageResource(R.drawable.home)
//                    fm.beginTransaction().hide(visibleWebView).show(writeupWebView).commit()
//                    visibleWebView = writeupWebView
//
//                    fab.setOnClickListener {
//                        fab.setImageResource(R.drawable.ic_baseline_add_24)
//                        fm.beginTransaction().hide(visibleWebView).show(homeFrag).commit()
//                        visibleWebView = homeFrag
//                    }
//                    return@OnNavigationItemSelectedListener true
//                }
//            }
//            false
//        }