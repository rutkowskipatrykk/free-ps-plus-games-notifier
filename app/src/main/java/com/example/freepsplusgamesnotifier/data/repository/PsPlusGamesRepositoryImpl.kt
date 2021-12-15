package com.example.freepsplusgamesnotifier.data.repository

import com.example.freepsplusgamesnotifier.data.remote.PsPlusGamesService
import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto
import com.example.freepsplusgamesnotifier.data.remote.dto.SearchedGameDto
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyDto
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import retrofit2.Response
import javax.inject.Inject

class PsPlusGamesRepositoryImpl
@Inject
constructor(
    private val psPlusGamesService: PsPlusGamesService
) : PsPlusGamesRepository {

    override fun getListByDateInMillis(dateInMillis: Long): Response<List<GameListItemDto>> =
        psPlusGamesService.getGamesListByMillis(dateInMillis).execute()

    override fun getGameDetails(id: Int): Response<GameDetailsDto> =
        psPlusGamesService.getGamesDetails(id).execute()

    override fun getGameTrophies(id: Int): Response<List<TrophyDto>> =
        psPlusGamesService.getGameTrophies(id).execute()

    override fun searchGame(gameName: String): Response<List<SearchedGameDto>> =
        psPlusGamesService.getSearchedGames(gameName).execute()

}