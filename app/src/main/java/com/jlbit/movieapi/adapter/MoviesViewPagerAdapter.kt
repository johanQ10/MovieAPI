package com.jlbit.movieapi.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.jlbit.movieapi.fragment.movie.MoviePopularFragment
import com.jlbit.movieapi.fragment.movie.MovieTopRatedFragment
import com.jlbit.movieapi.fragment.movie.MovieUpcomingFragment

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