package com.farhan.androidproject.authentication.mostpopulararticles.models

import com.google.gson.annotations.SerializedName

data class MediaModel
    (val type:String,
     val subtype:String,
     val caption:String,
     val copyright:String,
     val approved_for_syndication:Int,
     @SerializedName("media-metadata")
     var mediametadata:List<MediaMetaModel>)
