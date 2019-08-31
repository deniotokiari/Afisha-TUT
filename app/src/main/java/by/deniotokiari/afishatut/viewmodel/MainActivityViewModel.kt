package by.deniotokiari.afishatut.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.deniotokiari.afishatut.api.City

class MainActivityViewModel(
    private val resources: Resources
) : ViewModel() {

    private val _menuTitle: MutableLiveData<String> = MutableLiveData()

    val menuTitle: LiveData<String>
        get() = _menuTitle

    fun updateTitle(city: City) {
        _menuTitle.value = resources.getString(city.title)
    }
}