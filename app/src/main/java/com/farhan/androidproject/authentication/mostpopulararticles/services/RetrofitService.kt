package com.farhan.androidproject.authentication.mostpopulararticles.services

import com.farhan.androidproject.authentication.mostpopulararticles.models.APIResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitService {

   // @GET("movielist.json")
    @GET("svc/mostpopular/v2/viewed/1.json?api-key=V6K9H9lDdr8fAjGPMloDqBcOZ9wgMPbz")
    fun getPopularArticles() : Call<APIResult>
    @GET("svc/mostpopular/v2/viewed/7.json?api-key=V6K9H9lDdr8fAjGPMloDqBcOZ9wgMPbz")
    fun getPopularArticlesWeakly() : Call<APIResult>
    @GET("svc/mostpopular/v2/viewed/30.json?api-key=V6K9H9lDdr8fAjGPMloDqBcOZ9wgMPbz")
    fun getPopularArticlesMonthly() : Call<APIResult>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                   // .baseUrl("https://howtodoandroid.com/")
                    .baseUrl("https://api.nytimes.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}