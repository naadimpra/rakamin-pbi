package dev.rakamin.myapplication

import dev.rakamin.myapplication.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getAllNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): NewsResponse

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): NewsResponse
}

object NewsApiConfig {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "02ae4db20ae94057a25a7a35582841ad"
    const val DEFAULT_QUERY = "us"
}
