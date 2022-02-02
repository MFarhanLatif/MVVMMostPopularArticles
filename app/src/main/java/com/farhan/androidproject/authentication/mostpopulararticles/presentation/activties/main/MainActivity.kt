package com.farhan.androidproject.authentication.mostpopulararticles.presentation.activties.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
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

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = PopularArticlesAdapter()


    lateinit var recyclerView: RecyclerView

    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepositoryImp(retrofitService))).get(
                MainViewModel::class.java
            )

        setUpRecyclerView(1)
        recyclerView.adapter = adapter
        /*   viewModel.getPopularArticles()
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
           })*/

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v) {

            button1 -> {
                setUpRecyclerView(1)
            }
            button2 -> {
                setUpRecyclerView(7)
            }
            button3 -> {
                setUpRecyclerView(30)
            }

        }
    }

    fun setUpRecyclerView(period: Int) {
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepositoryImp(retrofitService))).get(
                MainViewModel::class.java
            )
        recyclerView.adapter = adapter
        when (period) {
            7 -> {
                viewModel.getPopularArticlesWeakly()
            }
            30 -> {
                viewModel.getPopularArticlesMonthly()
            }
            else -> {
                viewModel.getPopularArticles()
            }
        }

        setButtonBackground(period)

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

    fun setButtonBackground(period: Int) {
        when (period) {
            7 -> {
                button1.setBackgroundColor(resources.getColor(R.color.black))
                button2.setBackgroundColor(resources.getColor(R.color.teal_200))
                button3.setBackgroundColor(resources.getColor(R.color.black))

            }
            30 -> {
                button1.setBackgroundColor(resources.getColor(R.color.black))
                button2.setBackgroundColor(resources.getColor(R.color.black))
                button3.setBackgroundColor(resources.getColor(R.color.teal_200))

            }
            else -> {
                button1.setBackgroundColor(resources.getColor(R.color.teal_200))
                button2.setBackgroundColor(resources.getColor(R.color.black))
                button3.setBackgroundColor(resources.getColor(R.color.black))

            }

        }
    }
}


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}