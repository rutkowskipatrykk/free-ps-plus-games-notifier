package com.example.freepsplusgamesnotifier.domain.use_case

import com.example.freepsplusgamesnotifier.common.exception.CallException
import com.example.freepsplusgamesnotifier.common.use_case.BaseNetworkUseCase
import com.example.freepsplusgamesnotifier.data.remote.dto.GameListItemDto
import com.example.freepsplusgamesnotifier.data.remote.dto.toGameListItem
import com.example.freepsplusgamesnotifier.domain.model.GameListItem
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import retrofit2.Response
import javax.inject.Inject

class GetGamesForSpecificMonthUseCase
@Inject
constructor(private val psPlusGamesRepository: PsPlusGamesRepository) :
    BaseNetworkUseCase<List<GameListItemDto>, List<GameListItem>>() {

    override fun makeCall(arguments: Array<Any>): Response<List<GameListItemDto>> {
        if (arguments[0] is Long) {
            return psPlusGamesRepository.getListByDateInMillis(arguments[0] as Long)
        } else {
            throw CallException()
        }
    }

    override fun handleData(data: List<GameListItemDto>) =
        data.map { it.toGameListItem() }

}