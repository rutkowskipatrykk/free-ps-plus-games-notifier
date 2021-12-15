package com.example.freepsplusgamesnotifier.domain.use_case

import com.example.freepsplusgamesnotifier.common.exception.CallException
import com.example.freepsplusgamesnotifier.common.use_case.BaseNetworkUseCase
import com.example.freepsplusgamesnotifier.data.remote.dto.SearchedGameDto
import com.example.freepsplusgamesnotifier.data.remote.dto.toSearchedGame
import com.example.freepsplusgamesnotifier.domain.model.SearchedGame
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import retrofit2.Response
import javax.inject.Inject

class GetSearchedGamesUseCase
@Inject
constructor(
    private val repository: PsPlusGamesRepository
) : BaseNetworkUseCase<List<SearchedGameDto>, List<SearchedGame>>() {

    override fun handleData(data: List<SearchedGameDto>): List<SearchedGame> {
        return data.map {
            it.toSearchedGame()
        }
    }

    override fun makeCall(arguments: Array<Any>): Response<List<SearchedGameDto>> {
        if (arguments[0] is String) {
            return repository.searchGame(arguments[0] as String)
        } else {
            throw CallException()
        }
    }

}
