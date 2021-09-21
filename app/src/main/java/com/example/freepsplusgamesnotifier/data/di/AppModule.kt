package com.example.freepsplusgamesnotifier.data.di

import com.example.freepsplusgamesnotifier.BuildConfig
import com.example.freepsplusgamesnotifier.common.Consts.API_BASE_URL
import com.example.freepsplusgamesnotifier.data.remote.PsPlusGamesService
import com.example.freepsplusgamesnotifier.data.repository.PsPlusGamesRepositoryImpl
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGamesApiService(): PsPlusGamesService {
        val interceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.NONE
            } else {
                HttpLoggingInterceptor.Level.BODY
            }
        }

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PsPlusGamesService::class.java)
    }

    @Provides
    @Singleton
    fun providePsPlusGameRepository(service: PsPlusGamesService): PsPlusGamesRepository =
        PsPlusGamesRepositoryImpl(service)

}