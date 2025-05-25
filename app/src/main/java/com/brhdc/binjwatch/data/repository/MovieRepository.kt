package com.brhdc.binjwatch.data.repository

import com.brhdc.binjwatch.data.models.Movie
import com.brhdc.binjwatch.data.models.TmdbMovieResponse
import com.brhdc.binjwatch.util.Constants.GET_MOVIE_DETAILS
import com.brhdc.binjwatch.util.Constants.GET_POPULAR_MOVIES
import com.google.firebase.database.core.Repo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieRepository {
    @GET(GET_POPULAR_MOVIES)
    suspend fun getPopularMovies(): Response<TmdbMovieResponse>

    @GET(GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("movieId") movieId: String): Response<Movie>
}