package com.example.freepsplusgamesnotifier.data.remote

import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PsPlusGamesService {

    @GET("/test/gameInfoListByDate")
    fun getGamesListByMilis(@Query("currentDate") timeInMilis: Long): Call<List<GameListItemDto>>

    @GET("TODO")
    fun getGamesDetails(id: Int): Call<GameDetailsDto>

}