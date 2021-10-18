package com.example.freepsplusgamesnotifier.domain.use_case

import com.example.freepsplusgamesnotifier.common.Resource
import com.example.freepsplusgamesnotifier.data.remote.dto.toGame
import com.example.freepsplusgamesnotifier.domain.exceptions.NoGameWasFoundException
import com.example.freepsplusgamesnotifier.domain.model.Game
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetGamesDetailsUseCase
@Inject
constructor(private val repository: PsPlusGamesRepository) {

    operator fun invoke(gameId: Int): Flowable<Resource<Game>> =
        Flowable.create({ emitter ->
            emitter.onNext(Resource.Loading())
            try {
                val game = repository.getGameDetails(gameId)?.toGame()
                if (game != null) {
                    emitter.onNext(Resource.Success(game))
                } else {
                    emitter.onError(NoGameWasFoundException())
                }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)

}