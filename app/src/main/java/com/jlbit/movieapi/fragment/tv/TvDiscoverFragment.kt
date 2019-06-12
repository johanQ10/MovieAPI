package com.jlbit.movieapi.fragment.tv

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
import com.jlbit.movieapi.adapter.TvAdapter
import com.jlbit.movieapi.client.Request
import com.jlbit.movieapi.model.MovieList
import com.jlbit.movieapi.model.TvList
import kotlinx.android.synthetic.main.fragment_popular.view.*
import org.jetbrains.anko.support.v4.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvDiscoverFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var request: Request
    private lateinit var tvList: TvList
    private var page: Int = 1

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_movie_discover, container, false)

        mainActivity = activity as MainActivity
        request = Request(context!!)

        mainActivity.actionBar.title = "   ${getString(R.string.tv_series)}"

        recyclerView = v.recycler_view

        val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(activity?.resources!!.getDrawable(R.drawable.divider))

        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(context)

        getTvList(request.apiKey,mainActivity.filter.sort_by,mainActivity.filter.year,page,mainActivity.filter.with_genres,
            mainActivity.filter.with_original_language)

        return v
    }

    private fun getTvList(api_key: String, sort_by: String, year: Int?, page: Int, with_genres: String, with_original_language: String){

        val call = request.retrofit().getDiscoverTv(api_key,sort_by,year,page,with_genres,with_original_language)

        call.enqueue(object : Callback<TvList> {
            override fun onResponse(call: Call<TvList>, response: Response<TvList>) {
                if(response.isSuccessful){

                    tvList = response.body()!!
                    recyclerView.adapter = TvAdapter(tvList.results, mainActivity, request, 4)

                    if(tvList.total_results == 0) longToast("There are no elements for the search")

                }else longToast("${response.code()}: ${response.message()}")
            }
            override fun onFailure(call: Call<TvList>, t: Throwable) {
                longToast(t.message.toString())
            }
        })
    }
}
