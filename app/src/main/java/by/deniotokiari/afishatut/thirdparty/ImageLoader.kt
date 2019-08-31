package by.deniotokiari.afishatut.thirdparty

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

interface ImageLoader {

    fun show(context: View, uri: String?, view: ImageView)

}

class GlideImageLoader : ImageLoader {

    override fun show(context: View, uri: String?, view: ImageView) {
        Glide.with(context).load(uri).into(view)
    }

}