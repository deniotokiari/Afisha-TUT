package by.deniotokiari.afishatut.api

import by.deniotokiari.afishatut.enitity.Event
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class AfishaTutParser(
    private val api: AfishaTutApi
) {

    fun getEvents(start: Long, end: Long? = null, city: City): List<Event>? {
        val uri: String = end?.let { api.getUriForDaysRange(start, it, city) } ?: api.getUriForDay(start, city)

        return try {
            val html: Document = Jsoup.connect(uri).get()

            html
                .select(".lists__li")
                .map {
                    val logo: String = it.select("img").attr("src")
                    val title: String = it.select(".name span").text()
                    val description: String = it.select(".txt p").text()
                    val link: String = it.select(".name").attr("href")

                    Event(
                        logo,
                        title,
                        description,
                        link
                    )
                }
        } catch (e: Exception) {
            null
        }
    }

}