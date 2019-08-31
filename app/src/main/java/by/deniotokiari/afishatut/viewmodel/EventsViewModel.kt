package by.deniotokiari.afishatut.viewmodel

import androidx.lifecycle.*
import by.deniotokiari.afishatut.api.AfishaTutParser
import by.deniotokiari.afishatut.api.City
import by.deniotokiari.afishatut.enitity.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventsViewModel(
    private val parser: AfishaTutParser
) : ViewModel() {

    private val _eventsByCategories: MutableMap<String, List<Event>> = mutableMapOf()
    private val _categories: MutableLiveData<Resource<List<String>>> = MutableLiveData()

    val categories: LiveData<Resource<List<String>>> = _categories
    val city: MutableLiveData<City> = MutableLiveData()
    val start: MutableLiveData<Long> = MutableLiveData()
    val end: MutableLiveData<Long> = MutableLiveData()
    val queryParams: MediatorLiveData<Triple<Long?, Long?, City?>> = MediatorLiveData()

    init {
        fun merge(start: Long?, end: Long?, city: City?) {
            queryParams.postValue(
                Triple(
                    start,
                    end,
                    city
                )
            )
        }

        queryParams.addSource(city) {
            merge(queryParams.value?.first, queryParams.value?.second, it)
        }
        queryParams.addSource(start) {
            merge(it, queryParams.value?.second, queryParams.value?.third)
        }
        queryParams.addSource(end) {
            merge(queryParams.value?.first, it, queryParams.value?.third)
        }
    }

    fun loadEvents(start: Long?, end: Long?, city: City?) {
        _categories.postValue(Resource.Loading())

        if (start != null && city != null) {
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
        } else {
            _categories.postValue(Resource.Error())
        }
    }

    fun refresh() {
        queryParams.value?.also { (start, end, city) ->
            loadEvents(start, end, city)
        }
    }

    fun getEventsByCategory(category: String): List<Event> = _eventsByCategories[category] ?: emptyList()
}