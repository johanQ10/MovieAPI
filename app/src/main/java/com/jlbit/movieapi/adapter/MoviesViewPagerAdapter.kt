package com.jlbit.movieapi.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import com.jlbit.movieapi.MainActivity
import com.jlbit.movieapi.R
import com.jlbit.movieapi.fragment.HomeFragment
import com.jlbit.movieapi.fragment.MoviePopularFragment
import com.jlbit.movieapi.fragment.MovieTopRatedFragment
import com.jlbit.movieapi.fragment.MovieUpcomingFragment

class MoviesViewPagerAdapter(fm: FragmentManager?,
                             private var totalTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> MoviePopularFragment()
            1 -> MovieTopRatedFragment()
            2 -> MovieUpcomingFragment()
            else -> null
        }
    }

    override fun getCount() = totalTabs
}