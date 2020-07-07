package com.dscepointblank.pointblank.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.databinding.FragmentLoginBinding
import com.dscepointblank.pointblank.notifications.MyNotifications

/* A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,
            R.layout.fragment_login, container, false)
         val  notifications = MyNotifications(this.requireContext())
        notifications.createNotification("Hey User!!","Point Blank Welcomes you")

        binding.up.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_login2_to_signup))
        return binding.root
    }
}
