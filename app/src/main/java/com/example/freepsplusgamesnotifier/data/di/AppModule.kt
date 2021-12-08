package com.example.freepsplusgamesnotifier.data.di

import com.example.freepsplusgamesnotifier.BuildConfig
import com.example.freepsplusgamesnotifier.common.Consts.API_BASE_URL
import com.example.freepsplusgamesnotifier.data.remote.PsPlusGamesService
import com.example.freepsplusgamesnotifier.data.repository.PsPlusGamesRepositoryImpl
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGamesApiService(): PsPlusGamesService {
        val interceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain
                        .request()
                        .newBuilder()
                        .build()
                    return chain.proceed(request)
                }
            })

        val gson = GsonBuilder().apply {
            registerTypeAdapter(Date::class.java, object : JsonDeserializer<Date> {
                override fun deserialize(
                    json: JsonElement?,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): Date {
                    return Date(json?.asJsonPrimitive?.asLong!!)
                }
            })
        }.create()

        val client = httpClient.build()
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PsPlusGamesService::class.java)
    }

    @Provides
    @Singleton
    fun providePsPlusGameRepository(service: PsPlusGamesService): PsPlusGamesRepository =
        PsPlusGamesRepositoryImpl(service)

}