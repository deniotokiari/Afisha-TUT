package by.deniotokiari.afishatut.viewmodel

sealed class Resource<T>(val data: T? = null) {

    class Success<T>(data: T) : Resource<T>(data)

    class Loading<T>(data: T? = null) : Resource<T>(data)

    class Error<T>(e: Throwable? = null, data: T? = null) : Resource<T>(data)

}