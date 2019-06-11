package com.jlbit.movieapi.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jlbit.movieapi.MainActivity
import com.jlbit.movieapi.adapter.MoviesViewPagerAdapter
import com.jlbit.movieapi.R
import com.jlbit.movieapi.adapter.TvViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: Any

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        mainActivity = activity as MainActivity

        tabLayout = v.tab_layout
        viewPager = v.view_pager

        if(mainActivity.type == 0) adapter = MoviesViewPagerAdapter(childFragmentManager, tabLayout.tabCount)
        else adapter = TvViewPagerAdapter(childFragmentManager, tabLayout.tabCount)

        viewPager.adapter = adapter as PagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        return v
    }
}
