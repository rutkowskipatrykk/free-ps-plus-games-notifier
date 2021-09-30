package com.example.freepsplusgamesnotifier.presentation.game_list.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.freepsplusgamesnotifier.common.Resource
import com.example.freepsplusgamesnotifier.domain.use_case.AddMonthToGivenDateUseCase
import com.example.freepsplusgamesnotifier.domain.use_case.GetGamesForSpecificMonthUseCase
import com.example.freepsplusgamesnotifier.domain.use_case.SubtractMonthToGivenDateUseCase
import com.example.freepsplusgamesnotifier.presentation.game_list.GameListState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject
constructor(
    private val getGamesForSpecificMonthUseCase: GetGamesForSpecificMonthUseCase,
    private val addMonthToGivenDateUseCase: AddMonthToGivenDateUseCase,
    private val subtractMonthToGivenDateUseCase: SubtractMonthToGivenDateUseCase
) : ViewModel() {

    private val _gameListState = mutableStateOf(GameListState())
    val gameListState: State<GameListState>
        get() = _gameListState

    private var date: Calendar = Calendar.getInstance()

    init {
        getGameListForDate()
    }

    fun addMonth() {
        date = addMonthToGivenDateUseCase(date)
        getGameListForDate()
    }

    fun subtractMonth() {
        date = subtractMonthToGivenDateUseCase(date)
        getGameListForDate()
    }

    fun convertIntDateToString(): String =
        SimpleDateFormat("MMMM yyyy").format(date.time)

    private fun getGameListForDate() {
        getGamesForSpecificMonthUseCase(date.timeInMillis)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    val state = when (it) {
                        is Resource.Loading -> GameListState(isLoading = true)
                        is Resource.Success -> GameListState(data = it.data)
                    }
                    _gameListState.value = state
                },
                {
                    Timber.e(it)
                    _gameListState.value = GameListState(error = it.message)
                }
            )
    }

}