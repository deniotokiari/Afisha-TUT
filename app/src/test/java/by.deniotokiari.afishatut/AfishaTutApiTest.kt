package by.deniotokiari.afishatut

import by.deniotokiari.afishatut.api.AfishaTutApi
import by.deniotokiari.afishatut.api.City
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject

class AfishaTutApiTest : AppKointTest() {

    private val api: AfishaTutApi by inject()

    @Test
    fun `should provide uri for day`() {
        val time = 1557774726187L // 2019.05.13

        Assert.assertEquals("https://afisha.tut.by/day/2019/05/13/", api.getUriForDay(time, City.MINSK))
        Assert.assertEquals("https://afisha.tut.by/day-grodno/2019/05/13/", api.getUriForDay(time, City.GRODNO))
    }

    @Test
    fun `should provide uri for days range`() {
        val start = 1557774726187L // 2019.05.13
        val end = 1557826762000L // 2019.05.14

        Assert.assertEquals("https://afisha.tut.by/day/2019-05-13/2019-05-14/", api.getUriForDaysRange(start, end, City.MINSK))
        Assert.assertEquals("https://afisha.tut.by/day-grodno/2019-05-13/2019-05-14/", api.getUriForDaysRange(start, end, City.GRODNO))
    }

}