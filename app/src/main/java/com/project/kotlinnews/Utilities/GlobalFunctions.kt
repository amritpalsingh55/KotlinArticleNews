package com.project.kotlinnews.Utilities

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.project.kotlinnews.R

/**
 * Created by it on 11/16/2019.
 */
class GlobalFunctions {
    companion object {
        // for network connection of device
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
        }

        internal fun showSnack(activity: Activity, msg: String, showAction: Boolean) {
            try {
                val snackbar = Snackbar.make(activity.getWindow().getDecorView().findViewById(android.R.id.content)
                        , msg, Snackbar.LENGTH_SHORT)
                val snackbarView = snackbar.getView()
                val textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text) as TextView
                textView.maxLines = 3
                if (showAction) {
                    snackbar.setAction(activity.getString(R.string.str_ok), View.OnClickListener { snackbar.dismiss() })
                    snackbar.setActionTextColor(ContextCompat.getColor(activity, android.R.color.white))
                }
                snackbar.show()
            } catch (e: Exception) {

            }

        }
    }
}