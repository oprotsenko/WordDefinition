package com.protsolo.worddefinition.utils.extentions

import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(android.app.Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}