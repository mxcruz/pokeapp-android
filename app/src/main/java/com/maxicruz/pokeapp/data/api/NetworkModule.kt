package com.maxicruz.pokeapp.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {

        val backEndUrl = "https://pokeapi.co/api/v2/"
        //val backEndUrl = BuildConfig.BASE_URL

        val client = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(backEndUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit) : IPokeApiService {
        return retrofit.create(IPokeApiService::class.java)
    }
}

