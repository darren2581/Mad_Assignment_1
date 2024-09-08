package com.example.madassignment1

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class Statistics : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(
            roundsPlayed: Int,
            player1Name: String,
            player2Name: String,
            player1Win: Int,
            player2Win: Int,
            player1Lose: Int,
            player2Lose: Int,
            player1Avatar: String,
            player2Avatar: String
        ): Statistics {
            val fragment = Statistics()
            val args = Bundle().apply {
                putInt("PLAYED", roundsPlayed)
                putString("PLAYER_NAME1", player1Name)
                putString("PLAYER_NAME2", player2Name)
                putInt("PLAYER1_WIN", player1Win)
                putInt("PLAYER2_WIN", player2Win)
                putInt("PLAYER1_LOSE", player1Lose)
                putInt("PLAYER2_LOSE", player2Lose)
                putString("PLAYER_AVATAR1", player1Avatar)
                putString("PLAYER_AVATAR2", player2Avatar)
            }
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: StatisticsViewModel by viewModels()
    private lateinit var player1: TextView
    private lateinit var player2: TextView
    private lateinit var avatar1: ImageView
    private lateinit var avatar2: ImageView
    private lateinit var win1: TextView
    private lateinit var win2: TextView
    private lateinit var lose1: TextView
    private lateinit var lose2: TextView
    private lateinit var rounds: TextView
    private lateinit var percent1: TextView
    private lateinit var percent2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the value from the arguments
        val receivedRounds = arguments?.getInt("PLAYED", 0) ?: 0
        val player1Name = arguments?.getString("PLAYER_NAME1") ?: ""
        val player2Name = arguments?.getString("PLAYER_NAME2") ?: ""
        val player1Win = arguments?.getInt("PLAYER1_WIN") ?: 0
        val player2Win = arguments?.getInt("PLAYER2_WIN") ?: 0
        val player1Lose = arguments?.getInt("PLAYER1_LOSE") ?: 0
        val player2Lose = arguments?.getInt("PLAYER2_LOSE") ?: 0
        val player1Avatar = arguments?.getString("PLAYER_AVATAR1") ?: ""
        val player2Avatar = arguments?.getString("PLAYER_AVATAR2") ?: ""

        // Update the ViewModel with the received data
        viewModel.setRoundsPlayed(receivedRounds)
        viewModel.setWinsPlayer1(player1Win)
        viewModel.setWinsPlayer2(player2Win)
        viewModel.setLosesPlayer1(player1Lose)
        viewModel.setLosesPlayer2(player2Lose)
        viewModel.setNamePlayer1(player1Name)
        viewModel.setNamePlayer2(player2Name)
        viewModel.setAvatarPlayer1(player1Avatar)
        viewModel.setAvatarPlayer2(player2Avatar)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player1 = view.findViewById(R.id.player1)
        player2 = view.findViewById(R.id.player2)
        avatar1 = view.findViewById(R.id.avatar1)
        avatar2 = view.findViewById(R.id.avatar2)
        win1 = view.findViewById(R.id.oneWin)
        win2 = view.findViewById(R.id.twoWin)
        lose1 = view.findViewById(R.id.oneLose)
        lose2 = view.findViewById(R.id.twoLose)
        rounds = view.findViewById(R.id.played)
        percent1 = view.findViewById(R.id.percent1)
        percent2 = view.findViewById(R.id.percent2)

        viewModel.roundsPlayed.observe(viewLifecycleOwner) { roundsPlayed ->
            rounds.text = "Rounds Played: $roundsPlayed"
        }

        viewModel.winsPlayer1.observe(viewLifecycleOwner) { winsPlayer1 ->
            win1.text = "W $winsPlayer1"
            updatePercentages(winsPlayer1, viewModel.losesPlayer1.value ?: 0, percent1)
        }

        viewModel.winsPlayer2.observe(viewLifecycleOwner) { winsPlayer2 ->
            win2.text = "W $winsPlayer2"
            updatePercentages(winsPlayer2, viewModel.losesPlayer2.value ?: 0, percent2)
        }

        viewModel.losesPlayer1.observe(viewLifecycleOwner) { losesPlayer1 ->
            lose1.text = "L $losesPlayer1"
            updatePercentages(viewModel.winsPlayer1.value ?: 0, losesPlayer1, percent1)
        }

        viewModel.losesPlayer2.observe(viewLifecycleOwner) { losesPlayer2 ->
            lose2.text = "L $losesPlayer2"
            updatePercentages(viewModel.winsPlayer2.value ?: 0, losesPlayer2, percent2)
        }

        viewModel.namePlayer1.observe(viewLifecycleOwner) { namePlayer1 ->
            player1.text = namePlayer1
        }

        viewModel.namePlayer2.observe(viewLifecycleOwner) { namePlayer2 ->
            player2.text = namePlayer2
        }

        viewModel.avatarPlayer1.observe(viewLifecycleOwner) { avatarPlayer1 ->
            val resId = when (avatarPlayer1) {
                "a1" -> R.drawable.a1
                "a2" -> R.drawable.a2
                "a3" -> R.drawable.a3
                "a4" -> R.drawable.a4
                "a5" -> R.drawable.a5
                "a6" -> R.drawable.a6
                else -> R.drawable.white_circle
            }
            avatar1.setImageResource(resId)
        }

        viewModel.avatarPlayer2.observe(viewLifecycleOwner) { avatarPlayer2 ->
            val resId = when (avatarPlayer2) {
                "a1" -> R.drawable.a1
                "a2" -> R.drawable.a2
                "a3" -> R.drawable.a3
                "a4" -> R.drawable.a4
                "a5" -> R.drawable.a5
                "a6" -> R.drawable.a6
                else -> R.drawable.white_circle
            }
            avatar2.setImageResource(resId)
        }
    }

    private fun calculateWinRate(wins: Int, losses: Int): Double {
        val totalGames = wins + losses
        return if (totalGames > 0) {
            (wins.toDouble() / totalGames) * 100
        } else {
            0.0
        }
    }

    private fun updatePercentages(wins: Int, losses: Int, percentTextView: TextView) {
        val winRate = calculateWinRate(wins, losses)
        percentTextView.text = String.format("Win Rate: %.2f%%", winRate)
    }
}

