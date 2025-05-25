package com.brhdc.binjwatch.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.brhdc.binjwatch.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateToDetails: (movieId: String) -> Unit,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val popularMovieListState by homeViewModel.popularMovieListState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.fetchPopularMovies()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        HomeContent(
            movieListState = popularMovieListState,
            modifier = Modifier.padding(innerPadding),
            onClickMovie = navigateToDetails
        )
    }
}
