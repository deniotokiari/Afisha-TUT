package by.deniotokiari.afishatut.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import by.deniotokiari.afishatut.MainActivityNavGraphDirections
import by.deniotokiari.afishatut.R
import by.deniotokiari.afishatut.extensions.observe
import by.deniotokiari.afishatut.viewmodel.EventsViewModel
import by.deniotokiari.afishatut.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: EventsViewModel by viewModel()
    private val menuViewModel: MenuViewModel by viewModel()
    private val navigationController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity, menu)

        observe(menuViewModel.city(viewModel.city)) {
            menu?.findItem(R.id.city)?.title = it
        }
        observe(menuViewModel.start(viewModel.start)) {
            menu?.findItem(R.id.start)?.title = it
        }
        observe(menuViewModel.end(viewModel.end)) {
            menu?.findItem(R.id.end)?.title = it
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.city -> {
                navigationController.navigate(MainActivityNavGraphDirections.openCityFilter())

                true
            }
            R.id.start -> {

                true
            }
            R.id.end -> {

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean = navigationController.navigateUp()
}
