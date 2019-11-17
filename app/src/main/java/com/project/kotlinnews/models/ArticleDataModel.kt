package com.project.kotlinnews.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by it on 11/16/2019.
 */
data class ArticleDataModel (
    @SerializedName("modhash") val modhash: String, @SerializedName("dist") val dist: Int,
    @SerializedName("children") val children: List<ChildModel>
)