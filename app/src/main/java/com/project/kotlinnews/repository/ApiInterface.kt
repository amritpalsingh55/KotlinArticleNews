package com.project.kotlinnews.repository

import com.project.kotlinnews.models.ArticleModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by it on 11/16/2019.
 */
interface ApiInterface {
    @GET(UrlConstants.KOTLIN_JSON)
    abstract fun getArticles(): Observable<ArticleModel>

}