package com.example.freepsplusgamesnotifier.domain.use_case

import com.example.freepsplusgamesnotifier.common.exception.CallException
import com.example.freepsplusgamesnotifier.common.use_case.BaseNetworkUseCase
import com.example.freepsplusgamesnotifier.data.remote.dto.GameDetailsDto
import com.example.freepsplusgamesnotifier.data.remote.dto.toGame
import com.example.freepsplusgamesnotifier.domain.model.Game
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import retrofit2.Response
import javax.inject.Inject

class GetGamesDetailsUseCase
@Inject
constructor(private val repository: PsPlusGamesRepository):
    BaseNetworkUseCase<GameDetailsDto, Game>(){

    override fun makeCall(arguments: Array<Any>): Response<GameDetailsDto> {
        if (arguments[0] is Int) {
            return repository.getGameDetails(arguments[0] as Int)
        } else {
            throw CallException()
        }
    }

    override fun handleData(data: GameDetailsDto) =
        data.toGame()

}