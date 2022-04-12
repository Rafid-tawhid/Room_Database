package com.example.pickers2.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.pickers2.R

class CustomAlertDialog(
    private val icon:Int= R.drawable.ic_baseline_delete_24,
    private val title:String,
    private val body:String,
    private val positiveButtonText:String,
    private val negativeButtonText:String,
    val positiveButtonClickcallBack:()->Unit
):DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder=AlertDialog.Builder(requireActivity()).apply {
            setTitle(title)
            setMessage(body)
            setIcon(icon)
            setPositiveButton(positiveButtonText) { dialogInterface, i ->

                positiveButtonClickcallBack()
            }
            setNegativeButton(negativeButtonText,null)
        }

        return builder.create()
    }

}