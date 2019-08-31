package by.deniotokiari.afishatut.di

import android.content.Context
import by.deniotokiari.afishatut.api.AfishaTutApi
import by.deniotokiari.afishatut.api.AfishaTutParser
import by.deniotokiari.afishatut.thirdparty.GlideImageLoader
import by.deniotokiari.afishatut.thirdparty.ImageLoader
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    single { AfishaTutApi() }

    single { AfishaTutParser(get()) }

    single<ImageLoader> { GlideImageLoader() }

    factory { get<Context>().getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }

}