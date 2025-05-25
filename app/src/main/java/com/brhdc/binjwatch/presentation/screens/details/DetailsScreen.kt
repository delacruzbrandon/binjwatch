package com.brhdc.binjwatch.presentation.screens.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.brhdc.binjwatch.presentation.viewmodels.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel = koinViewModel(), movieId: String) {
    val movieDetailsState by detailsViewModel.movieDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        detailsViewModel.fetchMovieDetails(movieId)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        DetailsContent(
            movieState = movieDetailsState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
