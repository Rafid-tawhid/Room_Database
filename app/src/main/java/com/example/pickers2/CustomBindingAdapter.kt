package com.example.pickers2

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setFavoriteIcon")
fun setFavoriteIcon(imageView: ImageView,favorite:Boolean){
    if(favorite){
       imageView.setImageResource(R.drawable.non_fav)
    }
    else{
        imageView.setImageResource(R.drawable.fav)
    }
}