package com.dscepointblank.pointblank.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.databinding.FragmentSplashBinding

//import com.dscepointblank.pointblank.notifications.MyNotifications

/**
 * A simple [Fragment] subclass.
 */
class Splash : Fragment() {
    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSplashBinding>(inflater,
            R.layout.fragment_splash, container, false)

        binding.button.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_splash_to_viewpager)
            )

        binding.notif.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_splash_to_notificatonactivity)
        )
        return binding.root
    }
}
