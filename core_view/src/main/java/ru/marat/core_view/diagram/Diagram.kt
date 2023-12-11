package ru.marat.core_view.diagram

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ru.marat.core_view.toPx
import kotlin.math.max


class Diagram @JvmOverloads constructor(
    context: Context,
    attrStyle: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrStyle, defStyle) {

    private val strokeWidth = 40f.toPx(this)
    private val spacingRadius = 5f

    private val paint = Paint().apply {
        color = Color.RED
        strokeWidth = this@Diagram.strokeWidth
        style = Paint.Style.STROKE
    }


    private var maxDimension = 0f
    private var fullValue = 0f
    var items: List<DiagramItem>? = null
        set(value) {
            field = value
            fullValue = 0f
            value?.forEach { item ->
                fullValue += item.value
            }
            invalidate()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val maxSize =
            max(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
        setMeasuredDimension(maxSize, maxSize)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        maxDimension = max(w, h).toFloat()
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        val padding = strokeWidth / 2f
        val arcSize = maxDimension - padding
            var sweepAngle = items?.first()?.value?.toSweepAngle()?:0f
        var startAngle = -(sweepAngle/2f)
        items?.forEach {
            sweepAngle = it.value.toSweepAngle()
            canvas?.drawArc(
                padding,
                padding,
                arcSize,
                arcSize,
                startAngle,
                sweepAngle,
                false,
                paint.apply {
                    color = it.color
                }
            )
            startAngle += sweepAngle
        }
    }

    private fun Float.toSweepAngle(reversed: Boolean = true): Float {
        val sweep = (this / fullValue)*360f
        return if (reversed) -sweep else sweep
    }
}