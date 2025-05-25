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

    private suspend fun getPopularMovieList(): RequestState<List<Movie>> {
        RequestState.Loading

        return try {
            val response = movieRepository.getPopularMovies()
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                val movieList = responseBody.results
                if (movieList.isEmpty()) {
                    RequestState.Idle
                } else {
                    RequestState.Success(movieList)
                }
            } else {
                RequestState.Idle
            }
        } catch (exception: HttpException) {
            RequestState.Error(exception)
        }
    }
}