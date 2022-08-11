package com.example.circularprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.circularprogressbar.ui.theme.CircularProgressBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Box(contentAlignment = Alignment.Center,
                modifier=Modifier.fillMaxSize()) {
                CircularProgressBar(percentage = 0.8f, number = 100)
            }

        }
    }
}

@Composable
fun CircularProgressBar(
    percentage:Float,
    number:Int,
    fontsize:TextUnit=25.sp,
    radius: Dp =50.dp,
    color: Color = Color.Green,
    strokewidth:Dp=8.dp,
    animationDuration:Int=1000,
    animationDelay:Int=0){
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    var currentPercentage = animateFloatAsState(targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )
    LaunchedEffect(key1 = true){
        animationPlayed=true
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius*2f)) {
        Canvas(modifier = Modifier.size(radius*2f), ){
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360*currentPercentage.value,
                useCenter = false,
                style = Stroke(strokewidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (currentPercentage.value * number).toInt().toString(),
            color=Color.DarkGray,
            fontSize = fontsize,
            fontWeight = FontWeight.Bold
        )
    }

} 