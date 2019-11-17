package com.project.kotlinnews.models

import com.google.gson.annotations.SerializedName

/**
 * Created by it on 11/15/2019.
 */
// to show article main data
data class ArticleModel (@SerializedName("kind") val kind: String,
    @SerializedName("data") var data: ArticleDataModel? = null)