package edu.msudenver.cs3013.mynewsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.msudenver.cs3013.mynewsapp.model.Article

class ArticleAdapter(private val articles: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.articleImage)
        val title: TextView = itemView.findViewById(R.id.articleTitle)
        val description: TextView = itemView.findViewById(R.id.articleDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        Glide.with(holder.image.context).load(article.urlToImage).into(holder.image)
        holder.title.text = article.title
        holder.description.text = article.description
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ArticleActivity::class.java)
            intent.putExtra("image", article.urlToImage)
            intent.putExtra("title", article.title)
            intent.putExtra("description", article.description)
            intent.putExtra("content", article.content)
            intent.putExtra("url", article.url)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = articles.size
}