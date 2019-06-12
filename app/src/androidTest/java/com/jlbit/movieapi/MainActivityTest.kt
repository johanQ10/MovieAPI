package com.jlbit.movieapi

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    var mActivity: MainActivity? = null

    @Before
    fun setUp() {
        mActivity = rule.activity
    }

    @After
    fun tearDown() {
        mActivity = null
    }

    @Test
    fun onCreate() {
        assertNotNull(mActivity?.actionBar)
        assertEquals(0,mActivity?.itemSelectedId)
        assertEquals(0,mActivity?.type)
    }
}