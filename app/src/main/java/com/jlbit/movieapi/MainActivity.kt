package com.jlbit.movieapi

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import com.jlbit.movieapi.adapter.MoviesAdapter
import com.jlbit.movieapi.client.Request
import com.jlbit.movieapi.fragment.HomeFragment
import com.jlbit.movieapi.fragment.movie.MovieDiscoverFragment
import com.jlbit.movieapi.fragment.tv.TvDiscoverFragment
import com.jlbit.movieapi.model.Filter
import com.jlbit.movieapi.model.Genre
import com.jlbit.movieapi.model.Genres
import com.jlbit.movieapi.model.MovieList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_filter.*
import kotlinx.android.synthetic.main.dialog_options.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.selector
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var actionBar: ActionBar
    private lateinit var request: Request
    lateinit var filter: Filter
    lateinit var genres: Genres
    var itemSelectedId: Int = 0
    var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        actionBar = supportActionBar!!

        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.setLogo(R.drawable.tmdb)

        actionBar.title = getString(R.string.web_name)

        request = Request(applicationContext)

        getGenres(request.apiKey,"")

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
            R.id.action_search -> {
                dialogFilter()
                return true
            }
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

    @SuppressLint("SetTextI18n")
    private fun dialogFilter(){
        val dialogView = Dialog(this)

        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogView.setContentView(R.layout.dialog_filter)
        dialogView.window?.setWindowAnimations(R.style.dialogAnimation)
        dialogView.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val editSortby = dialogView.edit_sortby
        val imageSortby = dialogView.image_sortby
        val editGenres = dialogView.edit_genres
        val imageGenres = dialogView.image_genres
        val linearAdult = dialogView.linear_adult
        val switchAdult = dialogView.switch_adult
        val linearVideo = dialogView.linear_video
        val switchVideo = dialogView.switch_adult
        val textYearTitle = dialogView.text_year_title
        val editYear = dialogView.edit_year
        val editLanguage = dialogView.edit_language
        val buttonFilter = dialogView.button_filter

        if(actionBar.title!!.substring(3) == getString(R.string.movies)){
            linearAdult.visibility = View.VISIBLE
            linearVideo.visibility = View.VISIBLE
            textYearTitle.text = getString(R.string.year)
        }else{
            linearAdult.visibility = View.GONE
            linearVideo.visibility = View.GONE
            textYearTitle.text = getString(R.string.first_air_date_year)
        }

        val sortsValue = resources.getStringArray(R.array.sort_value_array).toList()
        val sortsKey = resources.getStringArray(R.array.sort_key_array).toList()
        var sortIndex = 0

        imageSortby.setOnClickListener{
            selector("Select:", sortsValue) { _,i ->
                editSortby.setText(sortsValue[i])
                sortIndex = i
            }
        }

        val genresName: MutableList<String> = mutableListOf()

        genres.genres.forEach { genresName.add(it.name) }

        imageGenres.setOnClickListener{
            selector("Select:", genresName) { _,i ->
                if(genresName[i] != editGenres.text.toString()) {

                    val length = editGenres.length()
                    val now = editGenres.text.toString().split(genresName[i])

                    if (now.size > 1) {
                        editGenres.setText("${now[0]}${now[1]}")
                        val new = editGenres.text.toString()

                        editGenres.setText(new.replace(",,", ","))
                        if (new[0] == ',') editGenres.setText(editGenres.text.toString().substring(1))
                        if (new[new.length - 1] == ',') editGenres.setText(
                            editGenres.text.toString().substring(
                                0,
                                new.length - 1
                            )
                        )
                    }

                    if (length == editGenres.length()) {
                        if (length == 0) editGenres.setText(genresName[i])
                        else editGenres.setText("${editGenres.text},${genresName[i]}")
                    }

                }else editGenres.setText("")
            }
        }

        buttonFilter.setOnClickListener {
            var year: Int? = null
            var genresResult = ""

            val total = editGenres.text.toString().split(",")

            for(i in 0 until total.size){
                for(j in 0 until genres.genres.size){
                    if(total[i] == genres.genres[j].name){
                        genresResult = "$genresResult,${genres.genres[j].id}"
                        break
                    }
                }
            }

            if(genresResult.length > 0) genresResult = genresResult.substring(1)

            if(editYear.length() > 0) year = editYear.text.toString().toInt()

            filter = Filter(request.apiKey,sortsKey[sortIndex],switchAdult.isChecked,switchVideo.isChecked,
                            genresResult,year,editLanguage.text.toString())

            if(actionBar.title!!.substring(3) == getString(R.string.movies))
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, MovieDiscoverFragment())
                    .addToBackStack(null)
                    .commit()
            else
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, TvDiscoverFragment())
                    .addToBackStack(null)
                    .commit()

            dialogView.dismiss()
        }

        dialogView.show()
    }

    private fun getGenres(api_key: String, language: String){
        val call = request.retrofit().getMovieGenres(api_key, language)

        call.enqueue(object : Callback<Genres> {
            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                if(response.isSuccessful){

                    genres = response.body()!!

                }else longToast("${response.code()}: ${response.message()}")
            }
            override fun onFailure(call: Call<Genres>, t: Throwable) {
                longToast(t.message.toString())
            }
        })
    }
}
