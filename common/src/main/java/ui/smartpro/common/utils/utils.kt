package ui.smartpro.common.utils

import android.content.Context
import android.widget.ImageView
import coil.ImageLoader
import coil.request.LoadRequest

fun useCoilToLoadPhoto(context: Context, imageView: ImageView, resId: Int, imageLink: String) {
    val request = LoadRequest.Builder(context)
        .data(imageLink)
        .target(
            onStart = {},
            onSuccess = { result ->
                imageView.setImageDrawable(result)
            },
            onError = {
                imageView.setImageResource(resId)
            }
        )
        .transformations(
//            CircleCropTransformation()
        )
        .size(100, 100)
        .build()

    ImageLoader(context).execute(request)
}
