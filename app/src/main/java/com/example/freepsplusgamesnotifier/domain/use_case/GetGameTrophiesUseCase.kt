package com.example.freepsplusgamesnotifier.domain.use_case

import com.example.freepsplusgamesnotifier.common.exception.CallException
import com.example.freepsplusgamesnotifier.common.use_case.BaseNetworkUseCase
import com.example.freepsplusgamesnotifier.data.remote.dto.TrophyDto
import com.example.freepsplusgamesnotifier.data.remote.dto.toTrophy
import com.example.freepsplusgamesnotifier.domain.model.Trophy
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import retrofit2.Response
import javax.inject.Inject

class GetGameTrophiesUseCase
@Inject
constructor(private val repository: PsPlusGamesRepository) :
    BaseNetworkUseCase<List<TrophyDto>, List<Trophy>>() {

    override fun handleData(data: List<TrophyDto>) = data.map { it.toTrophy() }

    override fun makeCall(arguments: Array<Any>): Response<List<TrophyDto>> {
        if (arguments[0] is Int) {
            return repository.getGameTrophies(arguments[0] as Int)
        } else {
            throw CallException()
        }
    }

}