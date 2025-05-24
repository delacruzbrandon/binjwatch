package com.brhdc.binjwatch.di

import com.brhdc.binjwatch.data.repository.MovieRepository
import com.brhdc.binjwatch.data.repository.MovieRepositoryImpl
import com.brhdc.binjwatch.presentation.viewmodels.HomeViewModel
import com.brhdc.binjwatch.util.Constants.API_KEY
import com.brhdc.binjwatch.util.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
val appModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("accept", "application/json") // Use your constant
                    .addHeader("Authorization", "Bearer $API_KEY") // Use your constant
                    .build()
                chain.proceed(newRequest)
            })
            // You can add other interceptors here (e.g., logging interceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MovieRepository> {
        get<Retrofit>().create(MovieRepository::class.java)
    }

    single {
        MovieRepositoryImpl(get())
    }

    viewModel {
        HomeViewModel(get())
    }
}