package com.example.freepsplusgamesnotifier.domain.repository

import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto
import com.example.freepsplusgamesnotifier.data.remote.dto.SearchedGameDto
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyDto

interface PsPlusGamesRepository {

    fun getListByDateInMillis(date: Long): List<GameListItemDto>
    fun getGameDetails(id: Int): GameDetailsDto?
    fun getGameTrophies(id: Int): List<TrophyDto>?
    fun searchGame(gameName: String): List<SearchedGameDto>?

}