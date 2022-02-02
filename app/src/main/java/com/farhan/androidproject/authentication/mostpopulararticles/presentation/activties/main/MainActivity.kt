package com.farhan.androidproject.authentication.mostpopulararticles.presentation.activties.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.farhan.androidproject.authentication.mostpopulararticles.R
import com.farhan.androidproject.authentication.mostpopulararticles.presentation.adpters.PopularArticlesAdapter
import com.farhan.androidproject.authentication.mostpopulararticles.repository.MainRepositoryImp
import com.farhan.androidproject.authentication.mostpopulararticles.services.RetrofitService
import com.farhan.androidproject.authentication.mostpopulararticles.presentation.activties.main.viewmodel.MainViewModel
import com.farhan.androidproject.authentication.mostpopulararticles.presentation.activties.main.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = PopularArticlesAdapter()

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepositoryImp(retrofitService))).get(
                MainViewModel::class.java
            )
        recyclerView.adapter = adapter
        viewModel.getPopularArticles()
        viewModel.popularArticlesList.observe(this, Observer {
            when (it) {

                is MainViewModel.ArticleState.Loading -> {


                }
                is MainViewModel.ArticleState.Success -> {
                    val list = it.data ?: emptyList()
                    adapter.setMovieList(list)
                }
                is MainViewModel.ArticleState.Error -> {
                    toast(it.message!!)
                }

            }
        })

    }


}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}