package com.farhan.androidproject.authentication.mostpopulararticles.presentation.activties.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farhan.androidproject.authentication.mostpopulararticles.models.APIResult
import com.farhan.androidproject.authentication.mostpopulararticles.models.ArticleModel
import com.farhan.androidproject.authentication.mostpopulararticles.repository.MainRepository
import com.farhan.androidproject.authentication.mostpopulararticles.repository.MainRepositoryImp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    //livedata of state
    private val _popularArticlesList = MutableLiveData<ArticleState<List<ArticleModel>>>()
    val popularArticlesList : LiveData<ArticleState<List<ArticleModel>>>  = _popularArticlesList
    val errorMessage = MutableLiveData<String>()

    fun getPopularArticles() {

        _popularArticlesList.postValue(ArticleState.Loading(null))

        val response = repository.getPopularArticles()
        response.enqueue(object : Callback<APIResult> {
            override fun onResponse(call: Call<APIResult>, response: Response<APIResult>) {
                if(response.isSuccessful) {

                    _popularArticlesList.postValue(ArticleState.Success(response.body()!!.results))
                }
            }

            override fun onFailure(call: Call<APIResult>, t: Throwable) {
                //errorMessage.postValue(t.message)
                _popularArticlesList.postValue(ArticleState.Error(null,t.message?:"error"))
            }
        })
    }
    fun getPopularArticlesWeakly() {

        _popularArticlesList.postValue(ArticleState.Loading(null))

        val response = repository.getPopularArticlesWeakly()
        response.enqueue(object : Callback<APIResult> {
            override fun onResponse(call: Call<APIResult>, response: Response<APIResult>) {
                if(response.isSuccessful) {

                    _popularArticlesList.postValue(ArticleState.Success(response.body()!!.results))
                }
            }

            override fun onFailure(call: Call<APIResult>, t: Throwable) {
                //errorMessage.postValue(t.message)
                _popularArticlesList.postValue(ArticleState.Error(null,t.message?:"error"))
            }
        })
    }

    fun getPopularArticlesMonthly() {

        _popularArticlesList.postValue(ArticleState.Loading(null))

        val response = repository.getPopularArticlesMonthly()
        response.enqueue(object : Callback<APIResult> {
            override fun onResponse(call: Call<APIResult>, response: Response<APIResult>) {
                if(response.isSuccessful) {

                    _popularArticlesList.postValue(ArticleState.Success(response.body()!!.results))
                }
            }

            override fun onFailure(call: Call<APIResult>, t: Throwable) {
                //errorMessage.postValue(t.message)
                _popularArticlesList.postValue(ArticleState.Error(null,t.message?:"error"))
            }
        })
    }
    //sealed class stateflow


    sealed class ArticleState<T>(
        val data: T? = null,
        val message: String? = null
    ) {
        class Success<T>(data: T) : ArticleState<T>(data)
        class Loading<T>(data: T? = null) : ArticleState<T>(data)
        class Error<T>(data: T? = null, message: String) : ArticleState<T>(data, message)
    }
}