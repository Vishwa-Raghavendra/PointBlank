package com.dscepointblank.pointblank.ui.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController

import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.utilityClasses.Constants
import kotlinx.android.synthetic.main.fragment_splash.view.*
import kotlin.random.Random


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(Looper.getMainLooper()).postDelayed( {
            if (!getOnBoardingValue())
            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            else
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        },2500)

        val view =  inflater.inflate(R.layout.fragment_splash, container, false)


        val text = "<font color=#00C853>Point</font> <font color=#fafafa>Blank</font>"
        view.splashPbTv.text = HtmlCompat.fromHtml(text,HtmlCompat.FROM_HTML_MODE_LEGACY)
        val random = Random.nextInt(Constants.tagLineSize)
        view.splashTagLineTV.text = Constants.tagLines[random]

        return view
    }

    private fun getOnBoardingValue():Boolean
    {
        val prefs = requireActivity().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        return prefs.getBoolean("finish",false)
    }
}
