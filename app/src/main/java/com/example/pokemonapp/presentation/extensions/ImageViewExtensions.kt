package com.example.pokemonapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toDrawable
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun ImageView.loadImageOrFallback(
    url: String?,
    @DrawableRes fallback: Int,
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

internal suspend fun String.urlToDrawable(context: Context): Drawable? = runCatching {
    withContext(Dispatchers.IO) {
        Picasso.with(context).load(this@urlToDrawable).get().toDrawable(context.resources)
    }
}.getOrNull()