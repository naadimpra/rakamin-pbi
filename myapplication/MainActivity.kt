package dev.rakamin.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.rakamin.myapplication.adapter.NewsAdapter
import dev.rakamin.myapplication.databinding.ActivityMainBinding
import dev.rakamin.myapplication.NewsRepository
import dev.rakamin.myapplication.viewmodel.NewsViewModel
import dev.rakamin.myapplication.viewmodel.NewsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository())
    }
    private val newsAdapter = NewsAdapter(mutableListOf())
    private var currentPage = 1
    private val pageSize = 20
    private val query = "us"
    private val apiKey = "02ae4db20ae94057a25a7a35582841ad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        fetchNews()

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    currentPage++
                    fetchNews()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun fetchNews() {
        newsViewModel.getAllNews(query, apiKey, currentPage, pageSize).observe(this, { response ->
            response?.let {
                newsAdapter.addArticles(it.articles)
            }
        })
    }
}
