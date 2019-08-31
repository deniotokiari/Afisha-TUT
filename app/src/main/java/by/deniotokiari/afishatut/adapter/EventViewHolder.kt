package by.deniotokiari.afishatut.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.deniotokiari.afishatut.enitity.Event
import by.deniotokiari.afishatut.thirdparty.ImageLoader
import kotlinx.android.synthetic.main.adapter_event.view.*

class EventViewHolder(
    view: View,
    private val imageLoader: ImageLoader,
    private val itemClickListener: View.OnClickListener
) : RecyclerView.ViewHolder(view) {

    fun bind(event: Event) {
        itemView.logo.apply {
            imageLoader.show(this, event.logo, this)
        }

        itemView.title.text = event.title
        itemView.description.text = event.description

        itemView.tag = event
        itemView.setOnClickListener(itemClickListener)
    }

}