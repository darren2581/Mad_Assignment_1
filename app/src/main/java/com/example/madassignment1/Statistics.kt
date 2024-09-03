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
        fun newInstance() = Statistics()
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

    // For calculation and updates
    private var roundsPlayed = 0
    private var winsPlayer1 = 0
    private var winsPlayer2 = 0
    private var losesPlayer1 = 0
    private var losesPlayer2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize your UI elements or set up listeners here
        player1 = view.findViewById(R.id.player1)
        player2 = view.findViewById(R.id.player2)
        avatar1 = view.findViewById(R.id.avatar1)
        avatar2 = view.findViewById(R.id.avatar2)
        win1 = view.findViewById(R.id.oneWin)
        win2 = view.findViewById(R.id.twoWin)
        lose1 = view.findViewById(R.id.oneWin)
        lose2 = view.findViewById(R.id.twoLose)
        rounds = view.findViewById(R.id.played)
        percent1 = view.findViewById(R.id.percent1)
        percent2 = view.findViewById(R.id.percent2)

        // Set up observers for LiveData
        viewModel.roundsPlayed.observe(viewLifecycleOwner) { roundsPlayed ->
            rounds.text = "Round $roundsPlayed"
        }

        viewModel.winsPlayer1.observe(viewLifecycleOwner) { winsPlayer1 ->
            win1.text = "W($winsPlayer1)"
        }

        viewModel.winsPlayer2.observe(viewLifecycleOwner) { winsPlayer2 ->
            win2.text = "W($winsPlayer2)"
        }

        viewModel.losesPlayer1.observe(viewLifecycleOwner) { losesPlayer1 ->
            lose1.text = "L($losesPlayer1)"
        }

        viewModel.losesPlayer2.observe(viewLifecycleOwner) { losesPlayer2 ->
            lose2.text = "L($losesPlayer2)"
        }

        // Set initial values
        viewModel.setRoundsPlayed(6)
        viewModel.setWinsPlayer1(2)
        viewModel.setWinsPlayer2(3)
        viewModel.setLosesPlayer1(3)
        viewModel.setLosesPlayer2(2)

//        player1.text = "Darren"
//        player2.text = "Zhe Yi"
//        avatar1.setImageResource(R.drawable.a1)
//        avatar2.setImageResource(R.drawable.a5)
//        win1.text = "W(2)"
//        win2.text = "W(3)"
//        lose1.text = "L(3)"
//        lose2.text = "L(2)"
//        rounds.text = "Round 6"
    }
}