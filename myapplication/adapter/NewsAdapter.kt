package dev.rakamin.myapplication.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.rakamin.myapplication.databinding.ItemArticleBinding
import dev.rakamin.myapplication.model.Article

class NewsAdapter(private val articles: MutableList<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                textViewTitle.text = article.title
                textViewDescription.text = article.description
                Glide.with(imageView.context)
                    .load(article.urlToImage)
                    .into(imageView)

                root.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    root.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    fun addArticles(newArticles: List<Article>) {
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
}
