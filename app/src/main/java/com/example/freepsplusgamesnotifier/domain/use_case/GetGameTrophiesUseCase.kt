package com.example.freepsplusgamesnotifier.domain.use_case

import com.example.freepsplusgamesnotifier.common.Resource
import com.example.freepsplusgamesnotifier.data.remote.dto.toTrophy
import com.example.freepsplusgamesnotifier.domain.model.Trophy
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetGameTrophiesUseCase
@Inject
constructor(private val repository: PsPlusGamesRepository) {

    operator fun invoke(gameId: Int): Flowable<Resource<List<Trophy>?>> =
        Flowable.create({ emitter ->
            emitter.onNext(Resource.Loading())
            try {
                val trophies = repository.getGameTrophies(gameId)?.map { it.toTrophy() }
                emitter.onNext(Resource.Success(trophies))
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)

}