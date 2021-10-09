package com.example.multipleviewrecyclerview

import android.content.Context
import android.view.View
import com.example.multipleviewrecyclerview.util.setToast

sealed class ClickListeners{

   class SnackBarAction(private val context: Context, private val message: String): View.OnClickListener{
       override fun onClick(v: View?) {
           setToast(context, message)
       }

   }
}