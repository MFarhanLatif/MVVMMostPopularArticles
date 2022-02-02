package com.farhan.androidproject.authentication.mostpopulararticles.repository

import com.farhan.androidproject.authentication.mostpopulararticles.models.APIResult
import retrofit2.Call

interface MainRepository {

    fun getPopularArticles(): Call<APIResult>
    fun getPopularArticlesWeakly(): Call<APIResult>
    fun getPopularArticlesMonthly(): Call<APIResult>
}