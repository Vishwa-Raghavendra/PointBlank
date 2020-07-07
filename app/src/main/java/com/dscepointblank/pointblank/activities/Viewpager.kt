package com.dscepointblank.pointblank.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.databinding.FragmentViewpagerBinding


class Viewpager : Fragment() {
    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentViewpagerBinding>(inflater,
            R.layout.fragment_viewpager, container, false)

        binding.next.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_viewpager_to_login2)
        )

        return binding.root
    }
}
