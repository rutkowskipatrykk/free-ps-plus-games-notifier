package com.example.freepsplusgamesnotifier.data.repository

import com.example.freepsplusgamesnotifier.data.remote.PsPlusGamesService
import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import javax.inject.Inject

class PsPlusGamesRepositoryImpl
@Inject
constructor(
    private val psPlusGamesService: PsPlusGamesService
): PsPlusGamesRepository {

    override fun getListByDateInMilis(timeInMili: Long): List<GameListItemDto> =
        psPlusGamesService.getGamesListByMilis(timeInMili).execute().body() ?: listOf()

    override fun getGameDetails(id: Int): GameDetailsDto? =
        psPlusGamesService.getGamesDetails(id).execute().body()

}