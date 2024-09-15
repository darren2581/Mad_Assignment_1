package com.example.madassignment1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_PLAYER_TURN = "playerTurn"

class TurnFragment : Fragment() {
    private var playerTurn: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerTurn = it.getString(ARG_PLAYER_TURN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_turn, container, false)

        // Update the TextView with the current player's turn
        val turnTextView: TextView = view.findViewById(R.id.turn)
        turnTextView.text = "$playerTurn's Turn"

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(playerTurn: String) =
            TurnFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PLAYER_TURN, playerTurn)
                }
            }
    }
}
