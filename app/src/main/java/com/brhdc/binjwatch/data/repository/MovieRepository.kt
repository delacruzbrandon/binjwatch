package com.brhdc.binjwatch.data.repository

import com.brhdc.binjwatch.data.models.TmdbMovieResponse
import com.brhdc.binjwatch.util.Constants.GET_POPULAR_MOVIES
import retrofit2.Response
import retrofit2.http.GET


interface MovieRepository {
    @GET(GET_POPULAR_MOVIES)
    suspend fun getPopularMovies(): Response<TmdbMovieResponse>
}