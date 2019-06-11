package com.jlbit.movieapi.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.jlbit.movieapi.fragment.tv.TvPopularFragment
import com.jlbit.movieapi.fragment.tv.TvTopRatedFragment
import com.jlbit.movieapi.fragment.tv.TvUpcomingFragment

class TvViewPagerAdapter(fm: FragmentManager?,
                         private var totalTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> TvPopularFragment()
            1 -> TvTopRatedFragment()
            2 -> TvUpcomingFragment()
            else -> null
        }
    }

    override fun getCount() = totalTabs
}