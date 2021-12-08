package com.example.freepsplusgamesnotifier.data.repository

import com.example.freepsplusgamesnotifier.data.remote.PsPlusGamesService
import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto
import com.example.freepsplusgamesnotifier.data.remote.dto.SearchedGameDto
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyDto
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import javax.inject.Inject

class PsPlusGamesRepositoryImpl
@Inject
constructor(
    private val psPlusGamesService: PsPlusGamesService
) : PsPlusGamesRepository {

    override fun getListByDateInMillis(dateInMillis: Long): List<GameListItemDto> =
        psPlusGamesService.getGamesListByMillis(dateInMillis).execute().body() ?: listOf()

    override fun getGameDetails(id: Int): GameDetailsDto? =
        psPlusGamesService.getGamesDetails(id).execute().body()

    override fun getGameTrophies(id: Int): List<TrophyDto>? =
        psPlusGamesService.getGameTrophies(id).execute().body()

    override fun searchGame(gameName: String): List<SearchedGameDto>? =
        psPlusGamesService.getSearchedGames(gameName).execute().body()

}