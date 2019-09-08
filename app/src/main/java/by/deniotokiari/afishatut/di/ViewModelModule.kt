package by.deniotokiari.afishatut.di

import by.deniotokiari.afishatut.viewmodel.EventsViewModel
import by.deniotokiari.afishatut.viewmodel.MenuViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {

    viewModel<MenuViewModel>()

    viewModel<EventsViewModel>()
}