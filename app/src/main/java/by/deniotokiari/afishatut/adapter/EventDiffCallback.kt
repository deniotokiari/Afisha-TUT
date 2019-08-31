package by.deniotokiari.afishatut.adapter

import androidx.recyclerview.widget.DiffUtil
import by.deniotokiari.afishatut.enitity.Event

class EventDiffCallback(private val new: List<Event>, private val old: List<Event>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem: Event = old[oldItemPosition]
        val newItem: Event = new[newItemPosition]

        return oldItem.title == newItem.title
    }

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

}