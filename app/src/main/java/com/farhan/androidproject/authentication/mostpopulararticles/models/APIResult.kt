package com.farhan.androidproject.authentication.mostpopulararticles.models

data class APIResult
    (val status:String,
     val copyright:String,
     val num_results:Int,
     val results:List<ArticleModel>)