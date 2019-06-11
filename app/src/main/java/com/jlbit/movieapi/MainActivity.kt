package com.jlbit.movieapi

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import com.jlbit.movieapi.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_options.*

class MainActivity : AppCompatActivity() {
    lateinit var actionBar: ActionBar
    var itemSelectedId: Int = 0
    var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        actionBar = supportActionBar!!

        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setLogo(R.drawable.tmdb)

        actionBar.title = getString(R.string.app_name)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, HomeFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_option -> {
                dialogOptions()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dialogOptions(){
        val dialogView = Dialog(this)

        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogView.setContentView(R.layout.dialog_options)
        dialogView.window?.setWindowAnimations(R.style.dialogAnimation)
        dialogView.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogView.linear_movie.setOnClickListener {
            type = 0

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, HomeFragment())
                .addToBackStack(null)
                .commit()

            dialogView.dismiss()
        }
        dialogView.linear_tv.setOnClickListener {
            type = 1

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, HomeFragment())
                .addToBackStack(null)
                .commit()

            dialogView.dismiss()
        }

        dialogView.show()
    }
}