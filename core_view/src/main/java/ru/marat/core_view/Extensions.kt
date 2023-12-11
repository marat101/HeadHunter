package ru.marat.core_view

import android.util.DisplayMetrics
import android.view.View
import kotlin.math.roundToInt


fun Float.toPx(view: View): Float {
    val displayMetrics: DisplayMetrics = view.context.resources.displayMetrics
    return this * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)
}

fun Float.roundToPx(view: View): Int {
    return toPx(view).roundToInt()
}