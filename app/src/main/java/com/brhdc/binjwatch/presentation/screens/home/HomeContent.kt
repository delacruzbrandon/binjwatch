package com.brhdc.binjwatch.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
fun HomeContent(movieListState: RequestState<List<Movie>>, modifier: Modifier = Modifier) {
    if (movieListState is RequestState.Success) {
        if (movieListState.data.isEmpty()) {
            EmptyScreen("Success but no data found")
        } else {
            MovieList(movieListState.data)
        }
    } else if (movieListState is RequestState.Loading) {
        LoadingScreen()
    }
    else {
        EmptyScreen("Error")
    }
}

@Composable
fun MovieList(movieList: List<Movie>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items (movieList) { movie ->
            MovieItem(movieItem = movie)
        }
    }
}

@Composable
fun MovieItem(movieItem: Movie, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            val imageUrl = IMAGE_BASE_URL+movieItem.imageUrl
            AsyncImage(
                model = imageUrl,
                contentDescription = "${movieItem.title} poster",
                modifier = Modifier
                    .size(width = 100.dp, height = 150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(
                    text = movieItem.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Text(
                        text = "Year: ${movieItem.releaseDate}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "⭐ ${movieItem.score}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    BinjwatchTheme {
        MovieItem(Movie(
            id = 950387,
            title = "A Minecraft Movie",
            imageUrl = "/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
            score = 6.527,
            releaseDate = "2025-03-31",
            description = "A Minecraft Movie",
        ))
    }
}