package com.dscepointblank.pointblank.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.adapters.ViewPagerAdapter
import com.dscepointblank.pointblank.fragments.AboutViewPager.*
import com.dscepointblank.pointblank.fragments.onbording.CodingContests
import com.dscepointblank.pointblank.fragments.onbording.Forum
import com.dscepointblank.pointblank.fragments.onbording.WriteUpFragment
import com.dscepointblank.pointblank.utilityClasses.DepthPageTransformer
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class PagerAboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_pager_about, container, false)
        val fragList = arrayListOf<Fragment>(
            Pic1Fragment(),
            Pic2Fragment(),
            Pic3Fragment(),
            Pic4Fragment(),
            Pic5Fragment(),
            Pic6Fragment()
        )

        val adapter =
            ViewPagerAdapter(fragList, requireActivity().supportFragmentManager, lifecycle)

        val viewPager2 = view.viewPager
        viewPager2.adapter = adapter
        viewPager2.setPageTransformer(DepthPageTransformer())
        return view
    }
}