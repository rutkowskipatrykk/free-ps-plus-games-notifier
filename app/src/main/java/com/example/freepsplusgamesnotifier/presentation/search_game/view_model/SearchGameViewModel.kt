package com.example.freepsplusgamesnotifier.presentation.search_game.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.freepsplusgamesnotifier.common.Consts.EMPTY_STRING
import com.example.freepsplusgamesnotifier.common.Resource
import com.example.freepsplusgamesnotifier.domain.use_case.GetSearchedGamesUseCase
import com.example.freepsplusgamesnotifier.presentation.search_game.SearchGameState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchGameViewModel
@Inject
constructor(private val searchedGamesUseCase: GetSearchedGamesUseCase) : ViewModel() {

    private val _state: MutableState<SearchGameState> = mutableStateOf(SearchGameState())
    val state: State<SearchGameState>
        get() = _state

    var phrase: String = EMPTY_STRING

    private val searchedPhrase: BehaviorSubject<String> = BehaviorSubject.create()

    init {
        searchedPhrase.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .debounce(2, TimeUnit.SECONDS)
            .subscribe {
                searchGame(it)
            }
    }

     fun insertGame(it: String) {
         phrase = it
        searchedPhrase.onNext(it)
        _state.value = SearchGameState(isLoading = true)
    }

    private fun searchGame(string: String) {
        searchedGamesUseCase(string)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    when (it) {
                        is Resource.Success -> {
                            _state.value = SearchGameState(it.data)
                        }
                        is Resource.Loading -> {
                            _state.value = SearchGameState(isLoading = true)
                        }
                        is Resource.Error -> {
                            _state.value = SearchGameState(error = it.message)
                        }
                    }
                },
                {
                    _state.value = SearchGameState(error = EMPTY_STRING, isLoading = false)
                }
            )
    }

}