package com.project.kotlinnews.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.project.kotlinnews.R
import com.project.kotlinnews.Utilities.GlobalFunctions
import com.project.kotlinnews.models.ArticleModel
import com.project.kotlinnews.repository.RestClient
import com.project.kotlinnews.views.adapters.ArticlesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

// For showing article list
class MainActivity : AppCompatActivity() {
    private var disposable: Disposable? = null // for api call and provide encapsulation

    // initialise api variable
    //The articleApiService object that is created lazily
    //when the first time it is used. After that it will be reused without creation
    private val articleApiService by lazy {
        RestClient.setupRestClient()
    }
    private lateinit var articleAdapter: ArticlesAdapter // adapter variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // checking network connection and calling Api to get articles
        if(GlobalFunctions.isNetworkAvailable(this)) {
            getArticlesFromApi()
        }
        else{
            GlobalFunctions.showSnack(this,getString(R.string.str_check_internet),true)
        }
    }

    //Api to get data from server
    fun getArticlesFromApi(){
        pbLoader.visibility = View.VISIBLE
        disposable = articleApiService.getArticles().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> showResponse(result)},
                        { error ->
                            pbLoader.visibility = View.GONE
                            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                )
    }


    // set data retrieved from API
    private fun showResponse(result: ArticleModel){
        pbLoader.visibility = View.GONE
        if(result.data?.children!=null && result.data?.children?.size!=0){
            articleAdapter = ArticlesAdapter(result.data!!.children, supportFragmentManager)
            rv_articles.adapter = articleAdapter
        }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
