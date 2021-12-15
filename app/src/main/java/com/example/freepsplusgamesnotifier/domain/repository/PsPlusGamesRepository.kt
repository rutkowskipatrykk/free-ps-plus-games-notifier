package com.example.freepsplusgamesnotifier.domain.repository

import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto
import com.example.freepsplusgamesnotifier.data.remote.dto.SearchedGameDto
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyDto
import retrofit2.Response

interface PsPlusGamesRepository {

    fun getListByDateInMillis(date: Long): Response<List<GameListItemDto>>
    fun getGameDetails(id: Int): Response<GameDetailsDto>
    fun getGameTrophies(id: Int): Response<List<TrophyDto>>
    fun searchGame(gameName: String): Response<List<SearchedGameDto>>

}