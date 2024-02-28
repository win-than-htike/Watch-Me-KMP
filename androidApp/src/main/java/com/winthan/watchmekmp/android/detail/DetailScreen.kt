package com.winthan.watchmekmp.android.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.winthan.watchmekmp.android.R
import com.winthan.watchmekmp.android.Red

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenState
) {

    Box(
        contentAlignment = Alignment.Center,
    ) {

        uiState.movie?.let { movie ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {

                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(320.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(20.dp),
                ) {

                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {

                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Red
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_button),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = "Start Watching Now", color = Color.White)

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Release in ${movie.releaseDate}".uppercase(),
                        style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(text = movie.description,
                        style = MaterialTheme.typography.bodySmall)
                    
                }

            }
        }

        if (uiState.loading) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                CircularProgressIndicator(
                    color = Color.Red
                )

            }
        }

    }

}

