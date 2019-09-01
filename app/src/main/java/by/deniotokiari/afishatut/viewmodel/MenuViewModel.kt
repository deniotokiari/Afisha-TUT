package by.deniotokiari.afishatut.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import by.deniotokiari.afishatut.api.City
import java.text.SimpleDateFormat
import java.util.*

class MenuViewModel(
    private val resources: Resources
) : ViewModel() {

    private val dateFormatter = SimpleDateFormat("dd MMM", Locale.getDefault())

    fun city(city: LiveData<City>): LiveData<String> = Transformations.map(city) { resources.getString(it.title) }

    fun start(start: LiveData<Long>): LiveData<String> = Transformations.map(start) {
        dateFormatter.format(it)
    }

    fun end(end: LiveData<Long>): LiveData<String> = Transformations.map(end) {
        dateFormatter.format(it)
    }
}