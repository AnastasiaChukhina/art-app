package com.itis.artapp.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.itis.artapp.R
import com.itis.artapp.presentation.MainActivity

fun Fragment.showMessage(message: String) {
    Snackbar.make(
        requireActivity().findViewById(R.id.container),
        message,
        Snackbar.LENGTH_LONG
    ).show()
}

fun Fragment.setBackButton() {
    (activity as MainActivity).supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
    }
}

fun Fragment.hideBackButton() {
    (activity as MainActivity).supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(false)
    }
}

fun Fragment.setTitle(
    name: String
) {
    (activity as MainActivity).supportActionBar?.apply {
        title = name
    }
}

fun Fragment.navigateBack() {
    findNavController().popBackStack()
}


