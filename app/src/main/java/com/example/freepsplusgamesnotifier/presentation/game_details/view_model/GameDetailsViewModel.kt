package com.example.freepsplusgamesnotifier.presentation.game_details.view_model

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.freepsplusgamesnotifier.R
import com.example.freepsplusgamesnotifier.common.Resource
import com.example.freepsplusgamesnotifier.data.remote.dto.PlatformType
import com.example.freepsplusgamesnotifier.domain.model.Game
import com.example.freepsplusgamesnotifier.domain.model.GameDetailsData
import com.example.freepsplusgamesnotifier.domain.model.Trophy
import com.example.freepsplusgamesnotifier.domain.use_case.GetGameTrophiesUseCase
import com.example.freepsplusgamesnotifier.domain.use_case.GetGamesDetailsUseCase
import com.example.freepsplusgamesnotifier.presentation.game_details.GameState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel
@Inject
constructor(
    private val getGamesDetailsUseCase: GetGamesDetailsUseCase,
    private val getGameTrophiesUseCase: GetGameTrophiesUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _gameState = mutableStateOf(GameState())
    val gameState: State<GameState>
        get() = _gameState

    private val _isListHorizontal = mutableStateOf(true)
    val isListHorizontal: State<Boolean>
        get() = _isListHorizontal

    val platformList: List<PlatformType>
        get() {
            var platformList = listOf<PlatformType>()
            _gameState.value.data?.game?.platforms?.let {
                platformList = Gson().fromJson(
                    it,
                    object : TypeToken<List<PlatformType>>() {}.type
                )
            }
            return platformList
        }

    private lateinit var gameDetailsFlowable: Flowable<Resource<Game>>
    private lateinit var trophiesFlowable: Flowable<Resource<List<Trophy>>>

    fun getDetails(gameId: Int) {
        _gameState.value = GameState(isDownloading = true)
        gameDetailsFlowable = getGamesDetailsUseCase(gameId)
        trophiesFlowable = getGameTrophiesUseCase(gameId)
        fetchData()
    }

    fun searchInGoogle() {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, _gameState.component1().data?.game?.name)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun searchInYoutube() {
        val intent = Intent(Intent.ACTION_SEARCH)
        intent.setPackage("com.google.android.youtube")
        intent.putExtra("query", _gameState.component1().data?.game?.name)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun searchInPsStore() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data =
            Uri.parse(URL("https://store.playstation.com/pl-pl/search/${_gameState.component1().data?.game?.name}").toString())
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun searchInMetacritic() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data =
            Uri.parse(URL("https://www.metacritic.com/search/all/${_gameState.component1().data?.game?.name}/results").toString())
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun changeListDirection() {
        _isListHorizontal.value = !_isListHorizontal.value
    }

    fun getButtonText() = if (_isListHorizontal.value) R.string.show_more else R.string.show_less


    private fun fetchData() {
        Flowable.zip(
            gameDetailsFlowable,
            trophiesFlowable,
            { t1, t2 ->
                val state = if (t1 is Resource.Loading || t2 is Resource.Loading) {
                    GameState(isDownloading = true)
                } else if (t1 is Resource.Success && t2 is Resource.Success) {
                    if (t1.data == null) {
                        GameState(error = context.getString(R.string.error))
                    } else {
                        GameState(GameDetailsData(t1.data, t2.data))
                    }
                } else {
                    GameState(error = context.getString(R.string.error))
                }
                state
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                _gameState.value = it
            }
    }

}