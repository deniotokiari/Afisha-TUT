package by.deniotokiari.afishatut.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import by.deniotokiari.afishatut.api.City

class MenuViewModel(
    private val resources: Resources
) : ViewModel() {

    fun city(city: LiveData<City>): LiveData<String> = Transformations.map(city) { resources.getString(it.title) }

    fun start(start: LiveData<Long>): LiveData<String> = Transformations.map(start) {
        "31 Aug"
    }

    fun end(end: LiveData<Long>): LiveData<String> = Transformations.map(end) {
        "2 Sep"
    }
}