package com.example.multipleviewrecyclerview.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.multipleviewrecyclerview.ClickListeners
import com.example.multipleviewrecyclerview.R
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


fun setImage(url: String, imageView: ImageView){
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .into(imageView)
}
fun setToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun setSnackBar(view: View, context: Context, message: String){
   val snackBar =  Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    snackBar.setAction("Ok", ClickListeners.SnackBarAction(context, message))
    snackBar.show()
}


fun enableView(view: View){
    view.isVisible= true
}

fun disableView(view: View){
    view.isVisible = false
}