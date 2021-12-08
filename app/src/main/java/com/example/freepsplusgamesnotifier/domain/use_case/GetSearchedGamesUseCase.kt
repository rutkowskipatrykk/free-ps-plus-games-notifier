package com.example.freepsplusgamesnotifier.domain.use_case

import com.example.freepsplusgamesnotifier.common.Resource
import com.example.freepsplusgamesnotifier.data.remote.dto.toSearchedGame
import com.example.freepsplusgamesnotifier.domain.model.SearchedGame
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetSearchedGamesUseCase
@Inject
constructor(
    private val repository: PsPlusGamesRepository
) {

    operator fun invoke(searchedGameName: String): Flowable<Resource<List<SearchedGame>>> =
        Flowable.create(
            { emitter ->
                emitter.onNext(Resource.Loading())
                try {
                    val games = repository.searchGame(searchedGameName)?.map { it.toSearchedGame() }
                    games?.let {
                        emitter.onNext(Resource.Success(it))
                    }
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }, BackpressureStrategy.BUFFER
        )

}