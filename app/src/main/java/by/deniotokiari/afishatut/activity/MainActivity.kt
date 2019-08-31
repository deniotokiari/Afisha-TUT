package by.deniotokiari.afishatut.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import by.deniotokiari.afishatut.R
import by.deniotokiari.afishatut.adapter.EventsViewPagerAdapter
import by.deniotokiari.afishatut.api.City
import by.deniotokiari.afishatut.extensions.newFragmentInstance
import by.deniotokiari.afishatut.extensions.preference
import by.deniotokiari.afishatut.fragment.CityFilterBottomDialogFragment
import by.deniotokiari.afishatut.viewmodel.MainActivityViewModel
import by.deniotokiari.afishatut.viewmodel.Resource
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()
    private val prefs: SharedPreferences by inject()

    private var currentCity: String by prefs.preference(City.MINSK.value)
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val adapter = EventsViewPagerAdapter(supportFragmentManager)

        view_pager.adapter = adapter

        viewModel.categories.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    progress.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    progress.visibility = View.GONE
                }
                is Resource.Success -> {
                    it
                        .data
                        ?.also { result ->
                            adapter.updateItems(result)
                        }

                    progress.visibility = View.GONE
                }
            }
        })
        viewModel.queryParams.observe(this, Observer { (start, end, city) ->
            city?.value?.also { currentCity = it }

            setMenuCityTitle(city)

            viewModel.loadEvents(start, end, city)
        })

        val startTime: Long = Calendar.getInstance().timeInMillis
        val endTime: Long = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            add(Calendar.DATE, 7)
        }.timeInMillis

        viewModel.queryParams.postValue(Triple(startTime, endTime, City.fromString(currentCity)))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity, menu)

        this.menu = menu

        setMenuCityTitle(viewModel.queryParams.value?.third)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.city -> {
                newFragmentInstance<CityFilterBottomDialogFragment>().show(supportFragmentManager, "city_filter")

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setMenuCityTitle(city: City?) {
        city?.title?.also { menu?.findItem(R.id.city)?.title = getString(it) }
    }

}
