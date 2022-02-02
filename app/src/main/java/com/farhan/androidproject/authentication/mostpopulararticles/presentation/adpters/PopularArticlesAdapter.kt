package com.farhan.androidproject.authentication.mostpopulararticles.presentation.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.farhan.androidproject.authentication.mostpopulararticles.models.ArticleModel
import com.farhan.androidproject.authentication.mostpopulararticles.R
import java.lang.Exception

class PopularArticlesAdapter : RecyclerView.Adapter<PopularArticlesViewHolder>() {

    private var popularArticleList = mutableListOf<ArticleModel>()

    fun setMovieList(movies: List<ArticleModel>) {
        this.popularArticleList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularArticlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_row_popular_articles, parent, false)
        return PopularArticlesViewHolder(
            view
        )
        /*val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return PopularArticlesViewHolder(binding)*/
    }

    override fun onBindViewHolder(holder: PopularArticlesViewHolder, position: Int) {


        try {
            val popularArticle = popularArticleList[position]
            holder.name.text = popularArticle.title
            holder.tvPublishDate.text = popularArticle.published_date
            holder.imageView.load(popularArticle.media[0].mediametadata[2].url)
            holder.tvDescription.text = popularArticle.abstract
            holder.tvCaption.text = popularArticle.media[0].caption
            holder.tvByLine.text = popularArticle.byline

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return popularArticleList.size
    }
}

/*class PopularArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




}*/

class PopularArticlesViewHolder internal constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    lateinit var tvCaption: TextView
    lateinit var name: TextView
    lateinit var tvPublishDate: TextView
    lateinit var tvDescription: TextView
    lateinit var imageView: ImageView
    lateinit var tvByLine: TextView
    override fun onClick(view: View) {}

    init {
        tvPublishDate = itemView.findViewById<View>(R.id.tvPublishDate) as TextView
        name = itemView.findViewById<View>(R.id.name) as TextView
        imageView = itemView.findViewById<View>(R.id.imageview) as ImageView
        tvDescription = itemView.findViewById<View>(R.id.tvDescription) as TextView
        tvCaption = itemView.findViewById<View>(R.id.tvCaption) as TextView
        tvByLine = itemView.findViewById<View>(R.id.tvByLine) as TextView
        itemView.setOnClickListener(this)
    }
}