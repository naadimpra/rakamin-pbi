package dev.rakamin.myapplication

import dev.rakamin.myapplication.RetrofitInstance

public class NewsRepository {
        suspend fun getAllNews(query: String, apiKey: String, page: Int, pageSize: Int) =
            RetrofitInstance.api.getAllNews(query, apiKey, page, pageSize)
    }