package com.farhan.androidproject.authentication.mostpopulararticles.repository

import com.farhan.androidproject.authentication.mostpopulararticles.models.APIResult
import com.farhan.androidproject.authentication.mostpopulararticles.models.ArticleModel
import com.farhan.androidproject.authentication.mostpopulararticles.services.RetrofitService
import retrofit2.Call

class MainRepositoryImp constructor(private val retrofitService: RetrofitService) : MainRepository {

    override fun getPopularArticles() : Call<APIResult> = retrofitService.getPopularArticles()
    override fun getPopularArticlesWeakly() : Call<APIResult> = retrofitService.getPopularArticlesWeakly()
    override fun getPopularArticlesMonthly():  Call<APIResult> = retrofitService.getPopularArticlesMonthly()



}