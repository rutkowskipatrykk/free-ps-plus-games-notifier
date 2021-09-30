package com.example.freepsplusgamesnotifier.data.remote

import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PsPlusGamesService {

    @GET("/GameListByDate/GetGameListByDate")
    fun getGamesListByMillis(
        @Query("date") date: Long,
        @Query("region") region: String
    ): Call<List<GameListItemDto>>

    @GET("TODO")
    fun getGamesDetails(id: Int): Call<GameDetailsDto>

}