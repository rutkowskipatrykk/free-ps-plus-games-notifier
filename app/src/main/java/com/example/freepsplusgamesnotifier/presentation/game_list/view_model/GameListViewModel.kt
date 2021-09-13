package com.example.freepsplusgamesnotifier.presentation.game_list.view_model

import androidx.lifecycle.ViewModel
import com.example.freepsplusgamesnotifier.domain.repository.PsPlusGamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject
constructor(psPlusGamesRepository: PsPlusGamesRepository) : ViewModel() {

    init {
        psPlusGamesRepository.getGameDetails(1)
    }

}