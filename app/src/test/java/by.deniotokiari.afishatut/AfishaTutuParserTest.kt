package by.deniotokiari.afishatut

import by.deniotokiari.afishatut.api.AfishaTutApi
import by.deniotokiari.afishatut.api.AfishaTutParser
import by.deniotokiari.afishatut.enitity.Event
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject

class AfishaTutuParserTest : AppKointTest() {

    private val parser: AfishaTutParser by inject()

    @Test
    fun `should parse events for today`() {
        val result: List<Event>? = parser.getEvents(
            start = System.currentTimeMillis(),
            city = AfishaTutApi.City.MINSK
        )

        Assert.assertNotNull(result)
    }

}