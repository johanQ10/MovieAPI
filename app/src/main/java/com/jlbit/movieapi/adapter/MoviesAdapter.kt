package com.jlbit.movieapi.adapter

import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jlbit.movieapi.MainActivity
import com.jlbit.movieapi.R
import com.jlbit.movieapi.client.Request
import com.jlbit.movieapi.fragment.MovieDetailFragment
import com.jlbit.movieapi.model.Movie
import kotlinx.android.synthetic.main.item.view.*
import org.jetbrains.anko.backgroundColor

class MoviesAdapter(private val movies: ArrayList<Movie>,
                    private val mainActivity: MainActivity,
                    private val request: Request,
                    private val type: Int) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide
            .with(mainActivity)
            .load("${request.urlImage}${movies[position].poster_path}")
            .centerCrop()
            .thumbnail(0.1f)
            .placeholder(R.drawable.load)
            .error(R.drawable.error)
            .into(holder.imageItem)

        when(type) {
            1 -> holder.textType.text = movies[position].popularity.toString()
            2 -> holder.textType.text = movies[position].vote_average.toString()
            3 -> holder.textType.text = movies[position].release_date
        }

        holder.textName.text = movies[position].title
        holder.textType.background = mainActivity.resources.getDrawable(R.drawable.bg_yellow_rectangle_radius)

        holder.itemView.setOnClickListener {
            mainActivity.itemSelectedId = movies[position].id

            holder.itemView.backgroundColor = mainActivity.resources.getColor(R.color.grayLight)

            mainActivity
                .supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
                .replace(R.id.frame_layout, MovieDetailFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imageItem: ImageView = itemView.image_item
        var textName: TextView = itemView.text_name
        var textType: TextView = itemView.text_type
    }
}