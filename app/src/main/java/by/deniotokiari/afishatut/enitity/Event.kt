package by.deniotokiari.afishatut.enitity

class Event(
    val logo: String,
    val title: String,
    val description: String,
    val uri: String
) {

    val category: String by lazy {
        uri
            .split("/")
            .getOrNull(3)
            ?.split("-")
            ?.first()
            ?: ""
    }

}