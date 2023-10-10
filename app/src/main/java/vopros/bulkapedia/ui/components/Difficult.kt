package vopros.bulkapedia.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.ui.theme.BulkapediaTheme
import kotlin.math.cos
import kotlin.math.sin

@Preview(showBackground = true)
@Composable
fun Difficult_Preview() {
    BulkapediaTheme {
        Surface(color = BulkaPediaTheme.colors.primaryDark) {
            Difficult(mechanics = 1f, speed = 1f, attack = 3f)
        }
    }
}

@Composable
fun Difficult(mechanics: Float, speed: Float, attack: Float) {
    SpiderChart(values = listOf(mechanics, speed, attack), maxValue = 3f, labels = listOf("Механики", "Скорость", "Урон"))
}

@Composable
fun SpiderChart(
    modifier: Modifier = Modifier,
    values: List<Float>,
    maxValue: Float,
    labels: List<String>
) {
    val textMeasurer = rememberTextMeasurer()
    val lineColor = BulkaPediaTheme.colors.secondary
    val tintColor = BulkaPediaTheme.colors.tintColor
    Canvas(modifier = modifier.fillMaxWidth().height(250.dp)) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val sides = values.size
        val angle = (360 / sides).toDouble()
        /* Draw lines */
        for (i in 0 until sides) {
            for (r in (maxValue * 10).toInt() downTo 0 step 10) {
                val radius = ((r / 10) / maxValue) * (size.width / 4)
                val x = centerX + radius * cos(Math.toRadians(i * angle))
                val y = centerY + radius * sin(Math.toRadians(i * angle))
                drawLine(
                    color = lineColor,
                    start = Offset(centerX, centerY),
                    end = Offset(x.toFloat(), y.toFloat()),
                    strokeWidth = 2.dp.toPx()
                )
                // find second dot
                val nextX = centerX + radius * cos(Math.toRadians((i + 1) * angle))
                val nextY = centerY + radius * sin(Math.toRadians((i + 1) * angle))
                drawLine(
                    color = lineColor,
                    start = Offset(x.toFloat(), y.toFloat()),
                    end = Offset(nextX.toFloat(), nextY.toFloat()),
                    strokeWidth = 2.dp.toPx()
                )
            }
            val labelX = centerX + (1f * (size.width / 4)) * cos(Math.toRadians(i * angle))
            val rad = when (i) {
                2 -> (1f * (size.width / 4)) + 18.sp.toPx()
                else -> 1f * (size.width / 4)
            }
            val labelY = centerY + rad * sin(Math.toRadians(i * angle))
            drawText(
                textMeasurer = textMeasurer,
                text = labels[i],
                topLeft = Offset(labelX.toFloat(), labelY.toFloat()),
                style = TextStyle(color = tintColor, fontSize = 12.sp)
            )
        }
        /* Draw value lines */
        for (i in 0 until sides) {
            val radius = (values[i] / maxValue) * (size.width / 4)
            val x = centerX + radius * cos(Math.toRadians(i * angle))
            val y = centerY + radius * sin(Math.toRadians(i * angle))
            drawLine(
                color = tintColor,
                start = Offset(centerX, centerY),
                end = Offset(x.toFloat(), y.toFloat()),
                strokeWidth = 2.dp.toPx()
            )
            // find second dot
            val value = try { values[i + 1] } catch (_: Exception) { values.first() }
            val nextRad = (value / maxValue) * (size.width / 4)
            val nextX = centerX + nextRad * cos(Math.toRadians((i + 1) * angle))
            val nextY = centerY + nextRad * sin(Math.toRadians((i + 1) * angle))
            drawLine(
                color = tintColor,
                start = Offset(x.toFloat(), y.toFloat()),
                end = Offset(nextX.toFloat(), nextY.toFloat()),
                strokeWidth = 2.dp.toPx()
            )
        }
        /* Draw fill zone */
        val points = mutableListOf<Pair<Float, Float>>()
        for (i in 0 until sides) {
            val radius = (values[i] / maxValue) * (size.width / 4)
            val x = centerX + radius * cos(Math.toRadians(i * angle))
            val y = centerY + radius * sin(Math.toRadians(i * angle))
            points.add(Pair(x.toFloat(), y.toFloat()))
        }
        val path = Path()
        path.moveTo(points[0].first, points[0].second)
        points.forEach { (x, y) -> path.lineTo(x, y) }
        drawPath(
            path = path,
            color = tintColor,
            alpha = 0.4f
        )
    }
}
