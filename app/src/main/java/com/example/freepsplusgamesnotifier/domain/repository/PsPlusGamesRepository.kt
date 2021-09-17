package com.example.freepsplusgamesnotifier.domain.repository

import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto

interface PsPlusGamesRepository {

    fun getListByDateInMilis(date: Long): List<GameListItemDto>
    fun getGameDetails(id: Int): GameDetailsDto?

}