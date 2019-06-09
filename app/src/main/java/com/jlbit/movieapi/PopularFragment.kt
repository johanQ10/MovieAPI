package com.jlbit.movieapi

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_popular.*
import android.R.id.tabs



class PopularFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_popular, container, false)

        val tabLayout = tab_layout as TabLayout

        tabLayout.setTabTextColors(resources.getColor(R.color.blue),resources.getColor(R.color.yellow))

        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    // ...
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    // ...
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    // ...
                }
            }
        )

        return v
    }
}
