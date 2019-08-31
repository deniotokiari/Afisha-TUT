package by.deniotokiari.afishatut.api

import androidx.annotation.StringRes
import by.deniotokiari.afishatut.R

sealed class City(val value: String, @StringRes val title: Int) {

    object MINSK : City("", R.string.CITY_MINSK)
    object GOMEL : City("-gomel", R.string.CITY_GOMEL)
    object GRODNO : City("-grodno", R.string.CITY_GRODNO)
    object VITEBSK : City("-vitebsk", R.string.CITY_VITEBSK)
    object BREST : City("-brest", R.string.CITY_BREST)
    object MOGILEV : City("-mogilev", R.string.CITY_MOGILEV)

    companion object {

        fun fromString(string: String): City {
            return when (string) {
                MINSK.value -> MINSK
                GOMEL.value -> GOMEL
                GRODNO.value -> GRODNO
                VITEBSK.value -> VITEBSK
                BREST.value -> BREST
                MOGILEV.value -> MOGILEV

                else -> throw UnsupportedOperationException()
            }
        }

    }

}