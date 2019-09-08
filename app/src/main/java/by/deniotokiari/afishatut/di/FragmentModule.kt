package by.deniotokiari.afishatut.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import by.deniotokiari.afishatut.extensions.fragment
import by.deniotokiari.afishatut.fragment.CityFilterBottomDialogFragment
import by.deniotokiari.afishatut.fragment.DatePickerDialogFragment
import by.deniotokiari.afishatut.fragment.EventsByCategoryFragment
import by.deniotokiari.afishatut.fragment.EventsFragment
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val fragmentModule: Module = module {

    fragment<EventsFragment>()

    fragment<EventsByCategoryFragment>()

    fragment<DatePickerDialogFragment>()

    fragment<CityFilterBottomDialogFragment>()

    factory<FragmentFactory> {
        object : FragmentFactory() {

            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return get(named(className))
            }
        }
    }
}