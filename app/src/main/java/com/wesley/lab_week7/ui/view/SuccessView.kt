package com.wesley.lab_week7.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.wesley.lab_week7.R
import com.wesley.lab_week7.ui.viewmodel.WeatherViewModel
import kotlin.math.roundToInt

@Composable
fun SuccessView(
    modifier: Modifier = Modifier,
    searchCity: String,
    onSearchChange: (String) -> Unit,
    viewModel: WeatherViewModel = viewModel()
) {
    val weather by viewModel.weather.collectAsState()
    val monthDate by viewModel.monthDate.collectAsState()
    val timeUpdated by viewModel.timeUpdated.collectAsState()
    val sunriseTime by viewModel.sunriseTime.collectAsState()
    val sunsetTime by viewModel.sunsetTime.collectAsState()
    val allCard by viewModel.allCard.collectAsState()

    val doSearch = {
        if (searchCity.isEmpty()) {
            viewModel.resetToHome()
        } else {
            viewModel.buttonSearchCity()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.weather___home_2),
            contentDescription = "sky background",
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )

        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchCity,
                    onValueChange = onSearchChange,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    placeholder = {
                        Text(
                            text = "Enter city name...", color = Color.White.copy(alpha = 0.7f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White.copy(alpha = 0.8f)
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.12f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.12f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    )
                )

                Spacer(modifier = modifier.width(8.dp))

                Button(
                    onClick = { doSearch() },
                    modifier = modifier.height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.12f))
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.White)
                    Spacer(modifier = modifier.width(4.dp))
                    Text(text = "Search", color = Color.White)
                }
            }

            Box(modifier = modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    contentPadding = PaddingValues(vertical = 24.dp)
                ) {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location Icon",
                                tint = Color.White,
                                modifier = modifier.size(28.dp)
                            )
                            Spacer(modifier = modifier.width(4.dp))
                            Text(
                                text = weather.city,
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 20.sp,
                                modifier = modifier.padding(top = 2.dp)
                            )
                        }
                        Spacer(modifier = modifier.height(10.dp))

                        Text(
                            text = monthDate,
                            color = Color.White,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = modifier.fillMaxWidth()
                        )

                        Spacer(modifier = modifier.height(6.dp))

                        Text(
                            text = timeUpdated,
                            color = Color.White.copy(alpha = 0.75f),
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center,
                            modifier = modifier.fillMaxWidth()
                        )
                        Spacer(modifier = modifier.height(70.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        model = "https://openweathermap.org/img/wn/${weather.icon}.png"
                                    ),
                                    contentDescription = "Weather Icon",
                                    modifier = modifier.size(100.dp)
                                )
                                Text(
                                    text = weather.condition,
                                    color = Color.White.copy(alpha = 0.9f),
                                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
                                )

                                Spacer(modifier = modifier.height(6.dp))

                                Text(
                                    text = "${weather.temperature.roundToInt()}°C",
                                    color = Color.White,
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontSize = 70.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        lineHeight = 96.sp
                                    )
                                )
                            }

                            Image(
                                painter = painterResource(
                                    id = when (weather.condition) {
                                        "Clear" -> {
                                            R.drawable.blue_and_black_bold_typography_quote_poster_3
                                        }

                                        "Rain" -> {
                                            R.drawable.blue_and_black_bold_typography_quote_poster_2
                                        }

                                        else -> {
                                            R.drawable.blue_and_black_bold_typography_quote_poster
                                        }
                                    }
                                ),
                                contentDescription = "Panda image",
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                        Spacer(modifier = modifier.height(90.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (i in 0..2) {
                                if (allCard.isNotEmpty()) {
                                    val card = allCard[i]
                                    DetailCard(
                                        icon = card.first,
                                        description = card.second,
                                        value = card.third
                                    )
                                }
                            }
                        }
                        Spacer(modifier = modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (i in 3..5) {
                                if (allCard.isNotEmpty()) {
                                    val card = allCard[i]
                                    DetailCard(
                                        icon = card.first,
                                        description = card.second,
                                        value = card.third
                                    )
                                }
                            }
                        }
                        Spacer(modifier = modifier.height(30.dp))
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.vector),
                                    contentDescription = "Sunrise Icon",
                                    modifier = modifier
                                        .size(20.dp)
                                )
                                Spacer(modifier = modifier.height(5.dp))
                                Text(
                                    text = "SUNRISE",
                                    color = Color.White.copy(alpha = 0.6f),
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = modifier.height(5.dp))
                                Text(
                                    text = sunriseTime,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = modifier.width(80.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.vector_21png),
                                    contentDescription = "Sunset Icon",
                                    modifier = modifier
                                        .size(20.dp)
                                )
                                Spacer(modifier = modifier.height(5.dp))
                                Text(
                                    text = "SUNSET",
                                    color = Color.White.copy(alpha = 0.6f),
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = modifier.height(5.dp))
                                Text(
                                    text = sunsetTime,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SuccessPreview() {
    SuccessView(searchCity = "", onSearchChange = {})
}