package com.project.kotlinnews.views.fragments


import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.project.kotlinnews.R
import com.project.kotlinnews.Utilities.AppConstants
import com.project.kotlinnews.models.ArticleDataDetailModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_article_detail.*


/**
 * A simple [Fragment] subclass.
 */
// For showing detail of selected article from recyclerview
class ArticleDetailFragment : Fragment(),View.OnClickListener {


    private lateinit var data:ArticleDataDetailModel //store parcelable data from bundle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    // initialise and set value of views
    private fun init(){
        ib_back.setOnClickListener(this)
        //receiving data from bundle
        data = arguments?.getParcelable(AppConstants.DATA) as ArticleDataDetailModel
        //assigning values
        tv_title.text = data.title
        tv_description.text = data.selftext


        // show image if available
        if(TextUtils.isEmpty(data.thumbnail)){
            sdv_image.visibility = View.GONE
        }
        else{
            sdv_image.setImageURI(Uri.parse(data.thumbnail),context)
        }
    }

    override fun onClick( view: View?) {
        when(view?.id){
            // performing back press action
            R.id.ib_back -> fragmentManager?.popBackStack()
        }

    }
}
