package by.deniotokiari.afishatut.api

import java.util.*

private const val AFISHA_TUT_BASE_URI = "https://afisha.tut.by/day%s"
// https://afisha.tut.by/day/2019/05/13/
private const val AFISHA_TUT_DAY_URI = "$AFISHA_TUT_BASE_URI/%d/%02d/%02d/"
// https://afisha.tut.by/day/2019-05-14/2019-05-15/
private const val AFISHA_TUT_DAYS_RANGE = "$AFISHA_TUT_BASE_URI/%d-%02d-%02d/%d-%02d-%02d/"

class AfishaTutApi {

    fun getUriForDay(time: Long, city: City): String = Calendar
        .getInstance()
        .apply { timeInMillis = time }
        .let { String.format(AFISHA_TUT_DAY_URI, city.value, it[Calendar.YEAR], it[Calendar.MONTH] + 1, it[Calendar.DAY_OF_MONTH]) }

    fun getUriForDaysRange(start: Long, end: Long, city: City): String {
        val startCalendar: Calendar = Calendar.getInstance().apply { timeInMillis = start }
        val endCalendar: Calendar = Calendar.getInstance().apply { timeInMillis = end }

        return String.format(
            AFISHA_TUT_DAYS_RANGE,
            city.value,

            startCalendar[Calendar.YEAR],
            startCalendar[Calendar.MONTH] + 1,
            startCalendar[Calendar.DAY_OF_MONTH],

            endCalendar[Calendar.YEAR],
            endCalendar[Calendar.MONTH] + 1,
            endCalendar[Calendar.DAY_OF_MONTH]
        )
    }

}