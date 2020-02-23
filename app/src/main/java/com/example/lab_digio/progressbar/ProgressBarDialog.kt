package kerry.express.th.mobile.ketracking.dialog.progressbar

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.lab_digio.R

class ProgressBarDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_progressbar,container,false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            if (!isStateSaved) {
                if (!isVisible)
                    super.show(manager, tag)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun dismiss() {
        try {
            if (!isStateSaved) {
                super.dismiss()
            } else {
                if (dialog != null && dialog?.isShowing == true) {
                    super.onDismiss(dialog!!)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    companion object {
        fun newInstance(): ProgressBarDialog {
            val fragment = ProgressBarDialog()
            fragment.isCancelable = false
            return fragment
        }
    }
}