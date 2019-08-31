package by.deniotokiari.afishatut.extensions

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView.ViewHolder> RecyclerView.Adapter<T>.dispatchUpdatesTo(callback: DiffUtil.Callback) {
    DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
}