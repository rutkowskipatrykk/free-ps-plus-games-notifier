package com.example.freepsplusgamesnotifier.domain.use_case

import com.example.freepsplusgamesnotifier.common.Resource
import com.example.freepsplusgamesnotifier.data.remote.dto.toGameListItem
import com.example.freepsplusgamesnotifier.domain.model.GameListItem
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableOnSubscribe
import javax.inject.Inject

class GetGamesForSpecificMonthUseCase
@Inject
constructor(private val psPlusGamesRepository: PsPlusGamesRepository) {

    operator fun invoke(dateInMilis: Long) =
        Flowable.create(FlowableOnSubscribe<Resource<List<GameListItem>>> { emitter ->
            emitter.onNext(Resource.Loading())
            try {
                val games = psPlusGamesRepository.getListByDateInMilis(dateInMilis)
                    .map { it.toGameListItem() }
                emitter.onNext(Resource.Success(games))
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(Exception("$e An error occured"))
            }
        }, BackpressureStrategy.BUFFER)

}