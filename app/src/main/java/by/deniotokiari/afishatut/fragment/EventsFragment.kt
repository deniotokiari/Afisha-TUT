package by.deniotokiari.afishatut.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.deniotokiari.afishatut.R
import by.deniotokiari.afishatut.adapter.EventsRecyclerVIewAdapter
import by.deniotokiari.afishatut.thirdparty.ImageLoader
import by.deniotokiari.afishatut.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_events.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EventsFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by sharedViewModel()
    private val imageLoader: ImageLoader by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = EventsRecyclerVIewAdapter(LayoutInflater.from(context), imageLoader) {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.uri)))
        }

        events_rv.apply {
            layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.events_col_span))
            this.adapter = adapter
        }

        arguments
            ?.getString("category")
            ?.also {
                adapter.updateItems(mainActivityViewModel.getEventsByCategory(it))
            }
    }

}