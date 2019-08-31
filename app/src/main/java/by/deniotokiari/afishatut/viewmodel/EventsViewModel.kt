package by.deniotokiari.afishatut.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.*
import by.deniotokiari.afishatut.api.AfishaTutParser
import by.deniotokiari.afishatut.api.City
import by.deniotokiari.afishatut.enitity.Event
import by.deniotokiari.afishatut.extensions.preference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class EventsViewModel(
    private val parser: AfishaTutParser,
    prefs: SharedPreferences
) : ViewModel() {

    private var prefsCity: String by prefs.preference(City.MINSK.value)
    private val _city: MutableLiveData<City> = MutableLiveData(City.fromString(prefsCity))
    private val _start: MutableLiveData<Long> = MutableLiveData(Calendar.getInstance().timeInMillis)
    private val _end: MutableLiveData<Long> = MutableLiveData(Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        add(Calendar.DATE, 7)
    }.timeInMillis)
    private val query: MediatorLiveData<Triple<Long?, Long?, City?>> = MediatorLiveData()
    private val _eventsByCategories: MutableMap<String, List<Event>> = mutableMapOf()
    private val _categories: MutableLiveData<Resource<List<String>>> = MutableLiveData()

    val categories: LiveData<Resource<List<String>>> = Transformations.switchMap(query) { (start, end, city) ->
        if (start != null && end != null && city != null) {
            loadEvents(start, end, city)
        }

        _categories
    }
    val city: LiveData<City>
        get() = _city
    val start: LiveData<Long>
        get() = _start
    val end: LiveData<Long>
        get() = _end

    init {
        query.addSource(_start) { query.postValue(Triple(it, _end.value, _city.value)) }
        query.addSource(_end) { query.postValue(Triple(_start.value, it, _city.value)) }
        query.addSource(_city) {
            prefsCity = it.value
            query.postValue(Triple(_start.value, _end.value, it))
        }
    }

    private fun loadEvents(start: Long, end: Long, city: City) {
        _categories.postValue(Resource.Loading())

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                parser.getEvents(start, end, city)
                    ?.also { result: List<Event> ->
                        _eventsByCategories.clear()
                        _eventsByCategories.putAll(
                            result
                                .filter { it.category.isNotEmpty() }
                                .groupBy { it.category }
                        )

                        _categories.postValue(
                            Resource.Success(
                                _eventsByCategories
                                    .keys
                                    .toList()
                            )
                        )
                    }
                    ?: _categories.postValue(Resource.Error())
            }
        }
    }

    fun refresh() {
        query.value = Triple(_start.value, _end.value, _city.value)
    }

    fun getEventsByCategory(category: String): List<Event> = _eventsByCategories[category] ?: emptyList()

    fun updateCity(city: City) {
        _city.value = City.fromString(city.value)
    }

    fun updateStart(start: Long) {
        _start.value = start
    }

    fun updateEnd(end: Long) {
        _end.value = end
    }
}