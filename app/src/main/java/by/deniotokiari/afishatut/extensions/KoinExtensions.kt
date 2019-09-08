package by.deniotokiari.afishatut.extensions

import androidx.fragment.app.Fragment
import org.koin.core.definition.BeanDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.experimental.builder.factoryBy

inline fun <reified T : Fragment> Module.fragment(): BeanDefinition<Fragment> {
    return factoryBy<Fragment, T>(named(T::class.java.name))
}