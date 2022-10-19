package com.example.pokemonapp.presentation.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.drawToBitmap
import androidx.palette.graphics.Palette
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun ImageView.loadImageOrFallback(
    url: String?,
    @DrawableRes fallback: Int
) = runCatching {
    Picasso.with(context)
        .load(url)
        .placeholder(fallback)
        .error(fallback)
        .into(this)
}.onFailure {
    Picasso.with(context)
        .load(fallback)
        .error(fallback)
        .into(this)
}

internal fun ImageView.loadImageOrFallback(
    url: String?,
    @DrawableRes fallback: Int,
    callback: Callback
) = runCatching {
    Picasso.with(context)
        .load(url)
        .placeholder(fallback)
        .error(fallback)
        .into(this, callback)
}.onFailure {
    Picasso.with(context)
        .load(fallback)
        .error(fallback)
        .into(this)
}

/*
* .get().apply {
            val palette = Palette.from(this).generate()
            palette.dominantSwatch?.let { view.backgroundTintList = ColorStateList.valueOf(it.rgb) }
        }*/

internal suspend fun String.urlToDrawable(context: Context): Drawable? = runCatching {
    withContext(Dispatchers.IO) {
        Picasso.with(context).load(this@urlToDrawable).get().toDrawable(context.resources)
    }
}.getOrNull()