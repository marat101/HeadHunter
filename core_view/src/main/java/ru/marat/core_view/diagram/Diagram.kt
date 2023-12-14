package ru.marat.core_view.diagram

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RadialGradient
import android.graphics.Shader.TileMode
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.graphics.ColorUtils
import ru.marat.core_view.R
import ru.marat.core_view.toPx
import kotlin.math.max
import kotlin.math.roundToInt


class Diagram @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    var type: DiagramType = DiagramType.LINE
        set(value) {
            field = value
            requestLayout()
        }
    private var strokeWidth = 0f
    private var spacingRadius: Float = 0f
    private var cornerRadius: Float = 4f.toPx(this) // todo

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        if (attrs != null) {
            val attrsArray = context.obtainStyledAttributes(attrs, R.styleable.Diagram)
            spacingRadius = attrsArray.getFloat(R.styleable.Diagram_spacing_radius, 10f)
            strokeWidth = attrsArray.getDimension(R.styleable.Diagram_strokeWidth, 25f.toPx(this))
            type = DiagramType.entries[attrsArray.getInteger(R.styleable.Diagram_diagram_type, 1)]
            attrsArray.recycle()
        }
    }

    private val dstOutPorterDuffMode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL_AND_STROKE
        xfermode = dstOutPorterDuffMode
    }

    private val diagramPaint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        strokeWidth = this@Diagram.strokeWidth
    }

    private var maxSize = 0f
    private var fullValue = 0f

    private val animator = ValueAnimator.ofFloat(0f).apply {
        addUpdateListener {
            invalidate()
        }
        interpolator = LinearInterpolator()
    }


    var items: List<DiagramItem> = listOf()
        set(value) {
            fullValue = 0f
            value.forEach { item ->
                fullValue += item.value
            }
            if (type == DiagramType.CIRCULAR) {
                val valuesCount = value.size.toLong()
                animator.duration = (((valuesCount- field.size) * 200)).coerceAtMost(3000)
                animator.setFloatValues(field.size.toFloat(), valuesCount.toFloat())
                animator.start()
            } else
                invalidate()
            field = value
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val maxSize =
            max(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
        val height = if (type == DiagramType.LINE) strokeWidth.roundToInt() else maxSize
        setMeasuredDimension(maxSize, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        maxSize = max(w, h).toFloat()
        if (type == DiagramType.CIRCULAR) {
            paint.shader = RadialGradient(
                maxSize / 2f, maxSize / 2f,
                maxSize, Color.BLACK, Color.TRANSPARENT, TileMode.MIRROR
            )
            diagramPaint.style = Paint.Style.STROKE
            diagramPaint.strokeCap = Paint.Cap.ROUND
            animator.start()
        } else {
            diagramPaint.style = Paint.Style.FILL
            diagramPaint.strokeCap = Paint.Cap.SQUARE
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (items.isNotEmpty())
            if (type == DiagramType.LINE)
                drawLineChart(canvas)
            else
                drawPieChart(canvas)
    }

    private fun Float.toSweepAngle(reversed: Boolean = true): Float {
        val spacing = if (items.size > 1) items.size * spacingRadius else 0f
        val sweep = (this / fullValue) * (360f - spacing)
        return if (reversed) -sweep else sweep
    }

    private fun drawLineChart(canvas: Canvas?) {
        var previousEndX = 0f
        items.forEach { item ->
            canvas?.drawRoundRect(
                0f,
                0f,
                (maxSize - previousEndX).coerceAtLeast(0f),
                strokeWidth,
                cornerRadius,
                cornerRadius,
                diagramPaint.apply {
                    color = item.color
                }
            )
            previousEndX += maxSize * (item.value / fullValue)
        }
    }

    private fun drawPieChart(canvas: Canvas?) {
        val padding = strokeWidth / 2f
        val arcSize = maxSize - padding
        var sweepAngle = items.first().value.toSweepAngle()
        var startAngle = -(sweepAngle / 2f)
        items.forEachIndexed { index, it ->
            sweepAngle = it.value.toSweepAngle()
            val animatedValue = animator.animatedValue as Float
            val animProgress = (animatedValue - index).coerceIn(0f, 1f)
            val animatedArcSize = arcSize * animProgress
            val animatedPadding = padding + (arcSize - animatedArcSize)
            val animatedColorAlpha =
                ColorUtils.setAlphaComponent(it.color, (255f * animProgress).toInt())
            canvas?.drawArc(
                animatedPadding,
                animatedPadding,
                animatedArcSize,
                animatedArcSize,
                startAngle,
                sweepAngle,
                false,
                diagramPaint.apply {
                    color = animatedColorAlpha
                }
            )
            startAngle += sweepAngle - spacingRadius
        }
        canvas?.drawOval(
            strokeWidth,
            strokeWidth,
            maxSize - strokeWidth,
            maxSize - strokeWidth,
            paint
        )
    }
}