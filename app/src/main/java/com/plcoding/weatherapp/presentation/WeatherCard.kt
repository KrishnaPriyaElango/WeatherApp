package com.plcoding.weatherapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@OptIn(ExperimentalAnimationGraphicsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter= painterResource(id = data.weatherType.iconRes)  ,
                    contentDescription = null,
                    modifier = Modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.temperatureCelsius} °C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.run {
                            vectorResource(
                                id = R.drawable.ic_pressure
                            )
                        })
                    WeatherDataDisplay(
                        value = data.humidity.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.run {
                            vectorResource(
                                id = R.drawable.ic_drop
                            )
                        })
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.run {
                            vectorResource(
                                id = R.drawable.ic_wind
                            )
                        })

                }
            }
        }

    }

}