package com.brhdc.binjwatch.data.repository

import com.brhdc.binjwatch.data.models.Movie
import com.brhdc.binjwatch.data.models.TmdbMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieRepository: MovieRepository
): MovieRepository {
    override suspend fun getPopularMovies(): Response<TmdbMovieResponse> {
        return withContext(Dispatchers.IO) {
            movieRepository.getPopularMovies()
        }
    }

    override suspend fun getMovieDetails(movieId: String): Response<Movie> {
        return withContext(Dispatchers.IO) {
            movieRepository.getMovieDetails(movieId)
        }
    }
}