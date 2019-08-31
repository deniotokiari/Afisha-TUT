package by.deniotokiari.afishatut

import by.deniotokiari.afishatut.di.appModule
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

open class AppKointTest : KoinTest {

    @Before
    fun before() {
        startKoin { modules(appModule) }
    }

    @After
    fun after() {
        stopKoin()
    }

}