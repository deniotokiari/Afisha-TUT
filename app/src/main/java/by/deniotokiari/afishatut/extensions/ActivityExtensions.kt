package by.deniotokiari.afishatut.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> AppCompatActivity.observe(data: LiveData<T>, onChange: (T) -> Unit) {
    data.observe(this, Observer<T>(onChange))
}