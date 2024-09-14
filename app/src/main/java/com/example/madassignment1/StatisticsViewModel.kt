package com.example.madassignment1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatisticsViewModel : ViewModel() {
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

    private val _namePlayer1 = MutableLiveData<String>()
    val namePlayer1: LiveData<String> get() = _namePlayer1

    private val _namePlayer2 = MutableLiveData<String>()
    val namePlayer2: LiveData<String> get() = _namePlayer2

    private val _avatarPlayer1 = MutableLiveData<String>()
    val avatarPlayer1: LiveData<String> get() = _avatarPlayer1

    private val _avatarPlayer2 = MutableLiveData<String>()
    val avatarPlayer2: LiveData<String> get() = _avatarPlayer2

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

    fun setNamePlayer1(name: String) {
        _namePlayer1.value = name
    }

    fun setNamePlayer2(name: String) {
        _namePlayer2.value = name
    }

    fun setAvatarPlayer1(avatar: String) {
        _avatarPlayer1.value = avatar
    }

    fun setAvatarPlayer2(avatar: String) {
        _avatarPlayer2.value = avatar
    }

    // New method to update values directly
    fun updateStatistics(
        roundsPlayed: Int,
        winsPlayer1: Int,
        winsPlayer2: Int,
        losesPlayer1: Int,
        losesPlayer2: Int
    ) {
        setRoundsPlayed(roundsPlayed)
        setWinsPlayer1(winsPlayer1)
        setWinsPlayer2(winsPlayer2)
        setLosesPlayer1(losesPlayer1)
        setLosesPlayer2(losesPlayer2)
    }
}