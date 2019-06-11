package com.jlbit.movieapi.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.*
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.jlbit.movieapi.MainActivity

import com.jlbit.movieapi.R
import com.jlbit.movieapi.client.Request
import com.jlbit.movieapi.model.MovieDetail
import com.jlbit.movieapi.model.Videos
import com.jlbit.movieapi.model.Video
import kotlinx.android.synthetic.main.fragment_detail_movie.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailFragment : Fragment(), YouTubePlayer.OnInitializedListener {
    private lateinit var mainActivity: MainActivity
    private lateinit var fragment: MovieDetailFragment
    private lateinit var request: Request
    private lateinit var movieDetail: MovieDetail
    private lateinit var videos: Videos

    private var videoKey = ""

    private lateinit var imageItem: ImageView
    private lateinit var textTitle: TextView
    private lateinit var textDate: TextView
    private lateinit var textLanguage: TextView
    private lateinit var textWeb: TextView
    private lateinit var textOverview: TextView
    private lateinit var textPopularity: TextView
    private lateinit var textVotes: TextView
    private lateinit var textVotesAverage: TextView
    private lateinit var linearVideos: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_detail_movie, container, false)

        mainActivity = activity as MainActivity
        fragment = this
        request = Request()

        imageItem = v.image_item
        textTitle = v.text_title
        textDate = v.text_date
        textLanguage = v.text_language
        textWeb = v.text_web
        textOverview = v.text_overview
        textPopularity = v.text_popularity
        textVotes = v.text_votes
        textVotesAverage = v.text_votes_average
        linearVideos = v.linear_videos

        getMovie(mainActivity.itemSelectedId, request.apiKey,"", "")

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar, menu)
        val item = menu?.findItem(R.id.action_option)
        item?.isVisible = false
        return super.onCreateOptionsMenu(menu,inflater)
    }

    private fun getMovie(movieId: Int, api_key: String, language: String, append_to_response: String){
        val call = request.retrofit().getMovieDetail(movieId, api_key, language, append_to_response)

        call.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if(response.isSuccessful){

                    movieDetail = response.body()!!

                    getVideos(movieId,api_key,language)

                    Glide
                        .with(mainActivity)
                        .load("${request.urlImage}${movieDetail.poster_path}")
                        .centerCrop()
                        .thumbnail(0.1f)
                        .placeholder(R.drawable.load)
                        .error(R.drawable.error)
                        .into(imageItem)

                    mainActivity.actionBar.title = movieDetail.title

                    textTitle.text = movieDetail.original_title
                    textDate.text = movieDetail.release_date
                    textLanguage.text = movieDetail.original_language
                    textWeb.text = movieDetail.homepage

                    textOverview.text = movieDetail.overview
                    textPopularity.text = movieDetail.popularity.toString()
                    textVotes.text = movieDetail.vote_count.toString()
                    textVotesAverage.text = movieDetail.vote_average.toString()

                }else longToast("${response.code()}: ${response.message()}")
            }
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                longToast(t.message.toString())
            }
        })
    }

    private fun getVideos(movieId: Int, api_key: String, language: String){
        val call = request.retrofit().getMovieVideos(movieId, api_key, language)

        call.enqueue(object : Callback<Videos> {
            override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                if(response.isSuccessful){

                    videos = response.body()!!

                    if(videos.results.size > 0) {

                        for(i in 0 until videos.results.size){

                            val ll = LinearLayout(mainActivity)

                            ll.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                            ll.gravity = Gravity.CENTER
                            ll.padding = 10

                            val tv = TextView(mainActivity)

                            val tp = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
                            tp.weight = 1.0F

                            tv.layoutParams = tp
                            tv.textColor = resources.getColor(R.color.grayDark)
                            tv.textSize = 14F
                            tv.typeface = Typeface.DEFAULT_BOLD
                            tv.text = videos.results[i].name

                            val iv = ImageView(mainActivity)

                            val ip = LinearLayout.LayoutParams(50, 50)
                            ip.setMargins(10,10,10,10)

                            iv.layoutParams = ip
                            iv.image = resources.getDrawable(R.drawable.play)

                            iv.setOnClickListener { showVideo(videos.results[i]) }

                            ll.addView(tv)
                            ll.addView(iv)
                            linearVideos.addView(ll)

                            if(i < videos.results.size - 1){
                                val view = View(mainActivity)

                                view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1)
                                view.backgroundColor = resources.getColor(R.color.blue)

                                linearVideos.addView(view)
                            }
                        }
                    }

                }else longToast("${response.code()}: ${response.message()}")
            }
            override fun onFailure(call: Call<Videos>, t: Throwable) {
                longToast(t.message.toString())
            }
        })
    }

    private fun showVideo(video: Video){
        videoKey = video.key

        val playerFragment = YouTubePlayerSupportFragment()
        playerFragment.initialize(request.youtubeApiKey, fragment)

        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, playerFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        if (!p2) {
            p1!!.cueVideo(videoKey)
            p1.setShowFullscreenButton(false)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        if (p1!!.isUserRecoverableError) toast("Error Player: ${p1.getErrorDialog(mainActivity,1)}")
        else toast("Error Player: $p1")
    }
}