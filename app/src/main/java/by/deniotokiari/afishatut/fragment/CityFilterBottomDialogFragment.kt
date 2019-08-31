package by.deniotokiari.afishatut.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.deniotokiari.afishatut.R
import by.deniotokiari.afishatut.api.City
import by.deniotokiari.afishatut.viewmodel.EventsViewModel
import kotlinx.android.synthetic.main.adapter_filter_city.view.*
import kotlinx.android.synthetic.main.fragment_bottom_filter_city.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CityFilterBottomDialogFragment : DialogFragment() {

    private val viewModel: EventsViewModel by sharedViewModel()

    private lateinit var filterAdapter: FilterRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_filter_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterAdapter = FilterRecyclerViewAdapter(
            requireContext(),
            viewModel,
            View.OnClickListener { adapterView ->
                val item: City = adapterView.tag as City

                viewModel.updateCity(item)

                dismiss()
            })

        cities_rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = filterAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        filterAdapter.updateItems(
            listOf(
                City.MINSK,
                City.GRODNO,
                City.BREST,
                City.GOMEL,
                City.MOGILEV,
                City.VITEBSK
            )
        )
    }

    private class FilterRecyclerViewAdapter(
        private val context: Context,
        private val viewModel: EventsViewModel,
        private val clickListener: View.OnClickListener
    ) : RecyclerView.Adapter<FilterViewHolder>() {

        private val items: MutableList<City> = mutableListOf()
        private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder = FilterViewHolder(
            context,
            clickListener,
            layoutInflater.inflate(R.layout.adapter_filter_city, parent, false)
        )

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
            val item: City = items[position]

            viewModel.city.value?.also { holder.bind(item, it) }
        }

        fun updateItems(items: List<City>) {
            this.items.clear()
            this.items.addAll(items)

            notifyDataSetChanged()
        }
    }

    private class FilterViewHolder(
        private val context: Context,
        private val clickListener: View.OnClickListener,
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: City, currentCity: City) {
            itemView.title.text = context.getText(item.title)
            itemView.tag = item
            itemView.setOnClickListener(clickListener)

            itemView.check_mark.visibility = if (currentCity.value == item.value) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}