package ru.marat.core_view

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class AppButton @JvmOverloads constructor(
    context: Context,
    attrStyle: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrStyle, defStyle) {

    val scope = CoroutineScope(Dispatchers.Main)

    private val gestureDetector = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).start();
            return true;
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        animate().scaleX(.9f).scaleY(.9f).setDuration(100).start()
        return super.onKeyDown(keyCode, event)
    }
}