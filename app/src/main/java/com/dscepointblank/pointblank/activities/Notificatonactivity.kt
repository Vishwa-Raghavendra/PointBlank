package com.dscepointblank.pointblank.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.databinding.FragmentNotificatonactivityBinding
import com.dscepointblank.pointblank.notifications.NotificationData
import com.dscepointblank.pointblank.notifications.PushNotification
import com.dscepointblank.pointblank.notifications.RetrofitInstance
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_notificatonactivity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

const val  TOPIC ="/topics/MyTopic"

class Notificatonactivity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentNotificatonactivityBinding>(inflater,
            R.layout.fragment_notificatonactivity, container, false)


        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        binding.button.setOnClickListener {
            //notifications.createNotification("Hey User!!","Point Blank Welcomes you")

            if(notititle.text.toString().isNotEmpty()&&notides.text.toString().isNotEmpty())
            {
                PushNotification(NotificationData(notititle.text.toString(),notides.text.toString()), TOPIC)
                    .also { sendNotification(it) }
            }

        }

        return binding.root

    }
    private fun sendNotification(notification:PushNotification) = GlobalScope.launch (
        Dispatchers.IO){
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if (response.isSuccessful)
            {
                Log.d("DDDD","Response is ${Gson().toJson(response.toString())}")
            }

        }catch (e: Exception)
        {
            Log.d("DDDD",e.localizedMessage!!)
        }
    }

}


