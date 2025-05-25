package com.brhdc.binjwatch.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brhdc.binjwatch.data.models.Movie
import com.brhdc.binjwatch.data.repository.MovieRepository
import com.brhdc.binjwatch.util.RequestState
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _movieDetailsState = MutableStateFlow<RequestState<Movie>>(RequestState.Idle)
    val movieDetailsState: StateFlow<RequestState<Movie>> = _movieDetailsState.asStateFlow()

    fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
            _movieDetailsState.value = getMovieDetails(movieId)
        }
    }

    private suspend fun getMovieDetails(movieId: String): RequestState<Movie> {
        val movieId = movieId.toString()
        RequestState.Loading

        return try {
            val response = movieRepository.getMovieDetails(movieId)
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                val movieDetails = responseBody
                RequestState.Success(movieDetails)
            } else {
                RequestState.Idle
            }
        } catch (exception: HttpException) {
            RequestState.Error(exception)
        }
    }
}