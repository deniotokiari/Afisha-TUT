package by.deniotokiari.afishatut.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.deniotokiari.afishatut.R
import by.deniotokiari.afishatut.adapter.EventsViewPagerAdapter
import by.deniotokiari.afishatut.extensions.observe
import by.deniotokiari.afishatut.viewmodel.EventsViewModel
import by.deniotokiari.afishatut.viewmodel.Resource
import kotlinx.android.synthetic.main.fragment_events.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EventsFragment : Fragment() {

    private val viewModel: EventsViewModel by sharedViewModel()

    private lateinit var adapter: EventsViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EventsViewPagerAdapter(requireFragmentManager())

        view_pager.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observe(viewModel.categories) {
            when (it) {
                is Resource.Loading -> {
                    progress.visibility = if (adapter.count > 0) View.GONE else View.VISIBLE
                }
                is Resource.Success -> {
                    progress.visibility = View.GONE

                    it.data?.also { result -> adapter.updateItems(result) }
                }
                is Resource.Error -> {
                    progress.visibility = View.GONE
                }
            }
        }
    }
}