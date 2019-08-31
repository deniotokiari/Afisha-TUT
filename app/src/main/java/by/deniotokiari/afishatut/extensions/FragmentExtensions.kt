package by.deniotokiari.afishatut.extensions

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <reified T : Fragment> newFragmentInstance(vararg params: Pair<String, Any>): T {
    return T::class.java.newInstance().apply { arguments = bundleOf(*params) }
}

inline fun <reified T : Fragment> newFragmentInstance(args: Bundle): T {
    return T::class.java.newInstance().apply { arguments = args }
}

fun <T> Fragment.observe(data: LiveData<T>, onChange: (T) -> Unit) {
    data.observe(viewLifecycleOwner, Observer<T>(onChange))
}