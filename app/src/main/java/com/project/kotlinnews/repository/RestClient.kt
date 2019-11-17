package com.project.kotlinnews.repository

import com.project.kotlinnews.Utilities.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by it on 11/16/2019.
 */
// Retrofit function setup to be used in api call
class RestClient {
    companion object {

        
         fun setupRestClient():ApiInterface {

            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(UrlConstants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service = retrofit.create(ApiInterface::class.java)
            return service
        }

    }



}