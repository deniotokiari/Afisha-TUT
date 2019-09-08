package by.deniotokiari.afishatut

import android.app.Application
import by.deniotokiari.afishatut.di.appModule
import by.deniotokiari.afishatut.di.fragmentModule
import by.deniotokiari.afishatut.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CurrentApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CurrentApplication)

            modules(
                listOf(
                    appModule,
                    viewModelModule,
                    fragmentModule
                )
            )
        }
    }

}