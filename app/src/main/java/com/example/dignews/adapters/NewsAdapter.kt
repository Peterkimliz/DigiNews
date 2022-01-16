package com.example.dignews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dignews.R
import com.example.dignews.models.Articles
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    private val differCallBack = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }
  val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=differ.currentList[position]
        holder.itemView.apply {
            Picasso.get().load(article.urlToImage).into(ivArticleImage)
            tvDescription.text=article.description
            tvPublishedAt.text=article.publishedAt
            tvSource.text=article.source.name
            tvTitle.text=article.title
            setOnClickListener {
                 onItemClickListener?.let { it(article) }
            }
        }
    }

    private var onItemClickListener:((Articles)->Unit)?=null

 fun setOnItemCLickListener(listener:((Articles)->Unit)){
        onItemClickListener=listener
    }

}