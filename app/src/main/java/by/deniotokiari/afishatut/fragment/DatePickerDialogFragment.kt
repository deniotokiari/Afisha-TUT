package by.deniotokiari.afishatut.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import by.deniotokiari.afishatut.viewmodel.EventsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val args: DatePickerDialogFragmentArgs by navArgs()
    private val viewMode: EventsViewModel by sharedViewModel()

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val value = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }.timeInMillis

        if (args.maxDate == -1L) {
            viewMode.updateEnd(value)
        } else {
            viewMode.updateStart(value)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = args.selectedDate
        }

        return DatePickerDialog(requireContext(), this, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]).also {
            it.datePicker.apply {
                minDate = args.minDate

                if (args.maxDate != -1L) {
                    maxDate = args.maxDate
                }
            }
        }
    }
}