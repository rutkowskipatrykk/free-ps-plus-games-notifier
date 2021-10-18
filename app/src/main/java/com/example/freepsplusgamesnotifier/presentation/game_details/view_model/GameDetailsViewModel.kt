package com.example.freepsplusgamesnotifier.presentation.game_details.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.freepsplusgamesnotifier.common.Resource
import com.example.freepsplusgamesnotifier.domain.use_case.GetGamesDetailsUseCase
import com.example.freepsplusgamesnotifier.presentation.game_details.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel
@Inject
constructor(private val getGamesDetailsUseCase: GetGamesDetailsUseCase) : ViewModel() {

    private val _gameState = mutableStateOf(GameState())
    val gameState: State<GameState>
        get() = _gameState

    fun getDetails(gameId: Int) {
        _gameState.value = GameState(isDownloading = true)
        getGamesDetailsUseCase(gameId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    val state = when (it) {
                        is Resource.Loading -> GameState(isDownloading = true)
                        is Resource.Success -> GameState(data = it.data)
                    }
                    _gameState.value = state
                }, {
                    _gameState.value = GameState(error = it.message)
                }
            )
    }

}