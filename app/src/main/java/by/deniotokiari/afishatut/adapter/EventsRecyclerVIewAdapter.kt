package by.deniotokiari.afishatut.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.deniotokiari.afishatut.R
import by.deniotokiari.afishatut.enitity.Event
import by.deniotokiari.afishatut.extensions.dispatchUpdatesTo
import by.deniotokiari.afishatut.thirdparty.ImageLoader

class EventsRecyclerVIewAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val itemClickCallback: (Event) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {

    private val items: MutableList<Event> = mutableListOf()
    private val itemClickListener: View.OnClickListener = View.OnClickListener {
        itemClickCallback(it.tag as Event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(layoutInflater.inflate(R.layout.adapter_event, parent, false), imageLoader, itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(items: List<Event>) {
        dispatchUpdatesTo(EventDiffCallback(items, this.items))

        this.items.clear()
        this.items.addAll(items)
    }

}