package com.dscepointblank.pointblank.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.WebScrap.codeforceActivity
import com.dscepointblank.pointblank.notifications.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

const val  TOPIC ="/topics/MyTopic"
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val  notifications = MyNotifications(this)

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        button.setOnClickListener {
            //notifications.createNotification("Hey User!!","Point Blank Welcomes you")

           if(notititle.text.toString().isNotEmpty()&&notides.text.toString().isNotEmpty())
           {
               PushNotification(NotificationData(notititle.text.toString(),notides.text.toString()), TOPIC)
                   .also { sendNotification(it) }
           }

        }


    }

    private fun sendNotification(notification:PushNotification) = GlobalScope.launch (Dispatchers.IO){
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if (response.isSuccessful)
            {
                Log.d("DDDD","Response is ${Gson().toJson(response.toString())}")
            }

        }catch (e:Exception)
        {
            Log.d("DDDD",e.localizedMessage!!)
        }
    }

    fun codeforcesClicked(view: View) {

        val intent = Intent(this,codeforceActivity::class.java)
        startActivity(intent)
    }
}
