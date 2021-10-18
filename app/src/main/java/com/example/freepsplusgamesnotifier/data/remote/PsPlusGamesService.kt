package com.example.freepsplusgamesnotifier.data.remote

import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PsPlusGamesService {

    @GET("/game-list/{date}")
    fun getGamesListByMillis(
        @Path("date") date: Long
    ): Call<List<GameListItemDto>>

    @GET("/game/{id}")
    fun getGamesDetails(@Path("id") id: Int): Call<GameDetailsDto>

}