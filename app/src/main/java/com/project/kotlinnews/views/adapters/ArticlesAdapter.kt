package com.project.kotlinnews.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.kotlinnews.R
import android.os.Bundle
import android.support.v4.app.FragmentManager

import android.text.TextUtils
import com.project.kotlinnews.Utilities.AppConstants
import com.project.kotlinnews.models.ChildModel
import com.project.kotlinnews.views.fragments.ArticleDetailFragment
import kotlinx.android.synthetic.main.adapter_articles.view.*

/**
 * Created by it on 11/16/2019.
 */
//Adapter for showing articles
class ArticlesAdapter(val items : List<ChildModel>, val fragmentManager:FragmentManager) : RecyclerView.Adapter<ArticlesAdapter.Articles>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Articles {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_articles,parent,false)
        return Articles(view)
    }

    override fun getItemCount(): Int {
        if(items.isNotEmpty()){
            return items.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: Articles, position: Int) {
        val data = items[position].data // getting article detail
        // setting title and image
        holder.tvTitle.text = data.title
        if (TextUtils.isEmpty(data.thumbnail)){
         holder.sdvImage.visibility = View.GONE
        }
        else{
            holder.sdvImage.setImageURI(data.thumbnail)
        }
        // performing click listener to show article detail
        holder.itemView.setOnClickListener {
            val fragment = ArticleDetailFragment()
            // storing data into bundle for passing to detail fragment
            val bundle = Bundle()
            bundle.putParcelable(AppConstants.DATA,items[position].data)
            fragment.arguments = bundle

            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right_fragment,
                    R.anim.fadeout, R.anim.fadein, R.anim.slide_out_right_fragment)
                    .addToBackStack(null)
                    .add(R.id.const_container, fragment).commit()
        }



    }
    // viewholder class
    class Articles (view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tv_title
        val sdvImage = view.sdv_image
    }
}