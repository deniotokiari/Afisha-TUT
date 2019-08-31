package by.deniotokiari.afishatut.di

import by.deniotokiari.afishatut.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {

    viewModel { MainActivityViewModel(get()) }

}