package com.winthan.watchmekmp.android.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.winthan.watchmekmp.android.R
import com.winthan.watchmekmp.android.Red
import com.winthan.watchmekmp.domain.model.Trailer


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenState
) {

    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center,
    ) {

        uiState.movie?.let { movie ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .verticalScroll(rememberScrollState()),
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

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Trailers",
                        style = MaterialTheme.typography.titleMedium)

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(uiState.trailers.size) { index ->
                            val trailer = uiState.trailers[index]
                            TrailerItem(trailer = trailer) {
                                val appIntent = Intent(
                                    Intent.ACTION_VIEW, Uri.parse(
                                        "vnd.youtube:${trailer.key}"
                                    )
                                )
                                val webIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("http://www.youtube.com/watch?v=${trailer.key}")
                                )
                                try {
                                    context.startActivity(appIntent)
                                } catch (ex: ActivityNotFoundException) {
                                    context.startActivity(webIntent)
                                }
                            }
                        }

                    }
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

@Composable
fun HtmlContentComposable(htmlContent: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            loadData(htmlContent, "text/html", "UTF-8")
        }
    })
}

@Composable
fun TrailerItem(
    trailer: Trailer,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(300.dp)
            .height(250.dp)
            .clickable {
                onClick()
            }
    ) {
        Box {
            AsyncImage(
                model = "https://img.youtube.com/vi/${trailer.key}/0.jpg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .background(Color.Black.copy(alpha = 0.6f))
                    .align(Alignment.Center),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.play_button),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .align(Alignment.BottomCenter),
            ) {
                Text(
                    text = trailer.name,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

        }
    }

}

