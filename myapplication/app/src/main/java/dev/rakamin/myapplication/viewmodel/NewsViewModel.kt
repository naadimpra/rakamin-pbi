package dev.rakamin.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import dev.rakamin.myapplication.NewsRepository
import kotlinx.coroutines.Dispatchers

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    fun getAllNews(query: String, apiKey: String, page: Int, pageSize: Int) = liveData(Dispatchers.IO) {
        val response = repository.getAllNews(query, apiKey, page, pageSize)
        emit(response)
    }

    fun searchNews(query: String, apiKey: String, page: Int, pageSize: Int) = liveData(Dispatchers.IO) {
        val response = repository.searchNews(query, apiKey, page, pageSize)
        emit(response)
    }
}

class NewsViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}
