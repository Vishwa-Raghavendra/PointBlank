package com.dscepointblank.pointblank.activities.viewPager

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2

import com.dscepointblank.pointblank.R
import kotlinx.android.synthetic.main.fragment_viewpager.*

class viewpagerFragment : Fragment(){

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "DSCE Forum",
                "Description Of DSCE Forum",
                R.drawable.code1
            ),
            IntroSlide(
                "PB Hustle",
                "Description Of PB Hustle",
                R.drawable.code2
            ),
            IntroSlide(
                "DSCE WriteUP",
                "Description of DSCE writeup",
                R.drawable.code3
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })


        buttonNext.setOnClickListener {
            if(introSliderViewPager.currentItem + 1< introSliderAdapter.itemCount){
                introSliderViewPager.currentItem += 1
            }else
            {
            }
        }
        tvSkipIntro.setOnClickListener {

        }
        return inflater.inflate(R.layout.fragment_viewpager, container, false)
    }


    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams= LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for(i in indicators.indices){
            indicators[i] = ImageView(requireActivity().applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(ContextCompat.getDrawable(requireActivity().applicationContext, R.drawable.indicator_inactive))
                this?.layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }
    private  fun setCurrentIndicator(index:Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(requireActivity().applicationContext, R.drawable.indicator_active))
            }

            else
            {
                imageView.setImageDrawable(ContextCompat.getDrawable(requireActivity().applicationContext, R.drawable.indicator_inactive))
            }
        }
    }

}
