package com.brhdc.binjwatch.presentation.screens.home

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
fun HomeContent(
    movieListState: RequestState<List<Movie>>,
    onClickMovie: (movieId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (movieListState is RequestState.Success) {
        if (movieListState.data.isEmpty()) {
            EmptyScreen("Success but no data found")
        } else {
            MovieList(
                movieList = movieListState.data,
                onClickMovie = onClickMovie
            )
        }
    } else if (movieListState is RequestState.Loading) {
        LoadingScreen()
    } else {
        EmptyScreen("Error")
    }
}

@Composable
fun MovieList(
    movieList: List<Movie>,
    modifier: Modifier = Modifier,
    onClickMovie: (movieId: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp,
            end = 12.dp,
            start = 12.dp,
        ),
        content = {
            items(movieList.size) { index ->
                MovieItem(
                    movieItem = movieList[index],
                    onClickMovie = onClickMovie
                )
            }
        },
    )
}

@Composable
fun MovieItem(
    movieItem: Movie,
    modifier: Modifier = Modifier,
    onClickMovie: (movieId: String) -> Unit
) {
    Card(
        onClick = { onClickMovie(movieItem.id.toString()) },
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
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(
                    text = movieItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = movieItem.releaseDate.toString(),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "⭐${movieItem.score}",
                        style = MaterialTheme.typography.bodySmall,

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
        MovieItem(
            Movie(
                id = 950387,
                title = "A Minecraft Movie",
                imageUrl = "/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
                score = 6.527,
                releaseDate = "2025-03-31",
                description = "A Minecraft Movie",
            ),
            onClickMovie = {}
        )
    }
}