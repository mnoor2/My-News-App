package edu.msudenver.cs3013.mynewsapp

import edu.msudenver.cs3013.mynewsapp.model.Article
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("from") from: String
    ): NewsResponse

    @GET("v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("from") from: String
    ): NewsResponse
}

data class NewsResponse(
    val articles: List<Article>
)