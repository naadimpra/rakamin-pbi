package dev.rakamin.myapplication.model

import dev.rakamin.myapplication.model.Article

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)