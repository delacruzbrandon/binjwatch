package com.brhdc.binjwatch.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.brhdc.binjwatch.components.screens.EmptyScreen
import com.brhdc.binjwatch.components.screens.LoadingScreen
import com.brhdc.binjwatch.data.models.Movie
import com.brhdc.binjwatch.ui.theme.BinjwatchTheme
import com.brhdc.binjwatch.util.Constants.IMAGE_BASE_URL
import com.brhdc.binjwatch.util.RequestState


@Composable
fun DetailsContent(movieState: RequestState<Movie>, modifier: Modifier = Modifier) {
    if (movieState is RequestState.Success) {
        MovieDetails(movieState.data)
    } else if (movieState is RequestState.Loading) {
        LoadingScreen()
    } else {
        EmptyScreen("Error")
    }
}

@Composable
fun MovieDetails(movie: Movie, modifier: Modifier = Modifier) {
    val imageUrl = IMAGE_BASE_URL+movie.imageUrl
    Column(
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 24.dp
        )
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = movie.releaseDate.toString(),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "⭐${movie.score}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        AsyncImage(
            model = imageUrl,
            contentDescription = "${movie.title} poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = movie.description.toString(),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
