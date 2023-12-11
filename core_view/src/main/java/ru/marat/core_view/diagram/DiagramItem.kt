package ru.marat.core_view.diagram

import android.graphics.Color
import androidx.annotation.ColorInt

data class DiagramItem(
    val name: String? = null,
    @ColorInt
    val color: Int = Color.GRAY,
    val value: Float = 0f
)