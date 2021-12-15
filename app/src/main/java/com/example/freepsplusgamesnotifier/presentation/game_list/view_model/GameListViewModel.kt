package com.example.freepsplusgamesnotifier.presentation.game_list.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.common.Consts.EMPTY_STRING
import com.example.freepsplusgamesnotifier.common.Resource
import com.example.freepsplusgamesnotifier.domain.model.ListMode
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

    private var date: Calendar = Calendar.getInstance()

    private val _gameListState = mutableStateOf(GameListState())
    val gameListState: State<GameListState>
        get() = _gameListState

    private val _displayDate = mutableStateOf(convertIntDateToString())
    val displayDate: State<String>
        get() = _displayDate

    private val _listOrientationMode = mutableStateOf(ListMode.TILE)
    val listModeMode: State<ListMode>
        get() = _listOrientationMode

    private val _oppositeListIcon: MutableState<Int> = mutableStateOf(-1)
    val oppositeListIcon: State<Int>
        get() = _oppositeListIcon

    init {
        setListOrientation(ListMode.LIST)
        getGameListForDate()
    }

    fun addMonth() {
        date = addMonthToGivenDateUseCase(date)
        _displayDate.value = convertIntDateToString()
        getGameListForDate()
    }

    fun subtractMonth() {
        date = subtractMonthToGivenDateUseCase(date)
        _displayDate.value = convertIntDateToString()
        getGameListForDate()
    }

    fun changeOrientation() {
        if (_listOrientationMode.value == ListMode.LIST) {
            setListOrientation(ListMode.TILE)
        } else {
            setListOrientation(ListMode.LIST)
        }
    }

    private fun setListOrientation(listMode: ListMode) {
        _listOrientationMode.value = listMode
        _oppositeListIcon.value = if (listMode == ListMode.LIST) {
            R.drawable.ic_baseline_view_module_24
        } else {
            R.drawable.ic_baseline_view_list_24
        }
    }

    private fun convertIntDateToString(): String =
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
                        is Resource.Error -> GameListState(error = EMPTY_STRING)
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