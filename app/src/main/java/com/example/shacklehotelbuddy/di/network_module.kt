package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.data.remote.PropertySearchService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//Should not be here because it's not supporting different urls for different environments. I'd use a config file for different flavours.
private const val BASE_URL = "https://hotels4.p.rapidapi.com/"

//these defo should not be here
private const val API_HOST = "hotels4.p.rapidapi.com"
private const val API_KEY = "26f4adfa6fmsha12649e2e5524edp16ce3cjsn497523e2eb78"

//OK to be here
private const val KEY_HEADER_API_KEY = "X-RapidAPI-Key"
private const val KEY_HEADER_API_HOST = "X-RapidAPI-Host"

val network_module = module {

    single<OkHttpClient> {
        val headerInterceptor = Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader(
                        KEY_HEADER_API_KEY, API_KEY
                    ).addHeader(
                        KEY_HEADER_API_HOST, API_HOST
                    )
                    .build()
            )
        }

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single { createRetrofit(get(), BASE_URL).create(PropertySearchService::class.java) }
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}



