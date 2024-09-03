package com.example.madassignment1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatisticsViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _roundsPlayed = MutableLiveData<Int>()
    val roundsPlayed: LiveData<Int> get() = _roundsPlayed

    private val _winsPlayer1 = MutableLiveData<Int>()
    val winsPlayer1: LiveData<Int> get() = _winsPlayer1

    private val _winsPlayer2 = MutableLiveData<Int>()
    val winsPlayer2: LiveData<Int> get() = _winsPlayer2

    private val _losesPlayer1 = MutableLiveData<Int>()
    val losesPlayer1: LiveData<Int> get() = _losesPlayer1

    private val _losesPlayer2 = MutableLiveData<Int>()
    val losesPlayer2: LiveData<Int> get() = _losesPlayer2

    fun setRoundsPlayed(rounds: Int) {
        _roundsPlayed.value = rounds
    }

    fun setWinsPlayer1(wins: Int) {
        _winsPlayer1.value = wins
    }

    fun setWinsPlayer2(wins: Int) {
        _winsPlayer2.value = wins
    }

    fun setLosesPlayer1(loses: Int) {
        _losesPlayer1.value = loses
    }

    fun setLosesPlayer2(loses: Int) {
        _losesPlayer2.value = loses
    }
}