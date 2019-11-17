package com.project.kotlinnews.models

import com.google.gson.annotations.SerializedName
/**
 * Created by it on 11/16/2019.
 */

data class ChildModel (@SerializedName("kind") val kind: String, @SerializedName("data") val data: ArticleDataDetailModel)