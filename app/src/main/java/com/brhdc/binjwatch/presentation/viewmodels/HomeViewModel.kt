package com.brhdc.binjwatch.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brhdc.binjwatch.data.models.Movie
import com.brhdc.binjwatch.data.models.TmdbMovieResponse
import com.brhdc.binjwatch.data.repository.MovieRepository
import com.brhdc.binjwatch.util.RequestState
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _popularMovieListState = MutableStateFlow<RequestState<List<Movie>>>(RequestState.Idle)
    val popularMovieListState: StateFlow<RequestState<List<Movie>>> = _popularMovieListState.asStateFlow()

    fun fetchPopularMovies() {
        viewModelScope.launch {
            _popularMovieListState.value = getPopularMovieList()
        }
    }

    suspend fun testPopularMovieResponse() {
        try {
            val response = movieRepository.getPopularMovies() // This is Response<TmdbMovieResponse>

            if (response.isSuccessful) {
                val movieData = response.body() // This is TmdbMovieResponse?
                if (movieData != null) {
                    Log.d("HomeViewModel_Test", "Successfully fetched popular movies: ${movieData.results.size} movies found.")
                    Log.d("HomeViewModel_Test", "${movieData.results}.")
                } else {
                    Log.w("HomeViewModel_Test", "Popular movies response was successful but body was null.")
                }
            } else {
                val errorCode = response.code()
                val errorBody = response.errorBody()?.string() // Read the error body as a string
                Log.e("HomeViewModel_Test", "Failed to fetch popular movies. Code: $errorCode, Error: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("HomeViewModel_Test", "Exception during fetch popular movies: ${e.message}", e)
        }
    }

    // Example of how you might call this suspend function from a non-suspend context in ViewModel
    fun triggerTestPopularMovieResponse() {
        viewModelScope.launch {
            testPopularMovieResponse()
        }
    }

    private suspend fun getPopularMovieList(): RequestState<List<Movie>> {
        RequestState.Loading

        return try {
            val response = movieRepository.getPopularMovies()
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                val movieList = responseBody.results
                RequestState.Success(movieList)
            } else {
                RequestState.Idle
            }
        } catch (exception: HttpException) {
            RequestState.Error(exception)
        }
    }
}