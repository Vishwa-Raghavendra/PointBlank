package com.dscepointblank.pointblank.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.databinding.FragmentSignupBinding

class Signup : Fragment() {
    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSignupBinding>(inflater,
            R.layout.fragment_signup, container, false)

        return binding.root
    }
}
