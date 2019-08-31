package by.deniotokiari.afishatut.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val picker = DatePickerDialog(requireContext(), this, 2019, 7, 31)

        return picker
    }
}