package com.jlbit.movieapi.fragment.movie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jlbit.movieapi.MainActivity

import com.jlbit.movieapi.R
import com.jlbit.movieapi.adapter.MoviesAdapter
import com.jlbit.movieapi.client.Request
import com.jlbit.movieapi.model.MovieList
import kotlinx.android.synthetic.main.fragment_popular.view.*
import org.jetbrains.anko.support.v4.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDiscoverFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var request: Request
    private lateinit var movieList: MovieList
    private var page: Int = 1

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_movie_discover, container, false)

        mainActivity = activity as MainActivity
        request = Request(context!!)

        mainActivity.actionBar.title = "   ${getString(R.string.movies)}"

        recyclerView = v.recycler_view

        val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(activity?.resources!!.getDrawable(R.drawable.divider))

        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(context)

        getMovieList(request.apiKey,mainActivity.filter.sort_by,mainActivity.filter.include_adult,
            mainActivity.filter.include_video,page,mainActivity.filter.with_genres,mainActivity.filter.year,
            mainActivity.filter.with_original_language)

        return v
    }

    private fun getMovieList(api_key: String, sort_by: String, include_adult: Boolean, include_video: Boolean,
                             page: Int, with_genres: String, year: Int?, with_original_language: String){

        val call = request.retrofit().getDiscoverMovies(api_key,sort_by,include_adult,include_video,page,with_genres,year,with_original_language)

        call.enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if(response.isSuccessful){

                    movieList = response.body()!!
                    recyclerView.adapter = MoviesAdapter(movieList.results, mainActivity, request, 4)

                    if(movieList.total_results == 0) longToast("There are no elements for the search")

                }else longToast("${response.code()}: ${response.message()}")
            }
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                longToast(t.message.toString())
            }
        })
    }
}
