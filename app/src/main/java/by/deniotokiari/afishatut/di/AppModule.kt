package by.deniotokiari.afishatut.di

import android.content.Context
import by.deniotokiari.afishatut.api.AfishaTutApi
import by.deniotokiari.afishatut.api.AfishaTutParser
import by.deniotokiari.afishatut.thirdparty.GlideImageLoader
import by.deniotokiari.afishatut.thirdparty.ImageLoader
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

val appModule: Module = module {

    single { get<Context>().resources }

    single<AfishaTutApi>()

    single<AfishaTutParser>()

    singleBy<ImageLoader, GlideImageLoader>()

    factory { get<Context>().getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
}