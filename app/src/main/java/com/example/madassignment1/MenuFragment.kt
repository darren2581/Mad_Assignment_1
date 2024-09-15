package com.example.madassignment1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

// Constants for the fragment's arguments
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MenuFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var gameMode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        // Find the buttons by their IDs
        val exitButton: Button = view.findViewById(R.id.exit)
        val profileButton: Button = view.findViewById(R.id.button2)

        // Access SharedPreferences using the activity context
        val prefs: SharedPreferences = requireActivity().getSharedPreferences("game_mode", Context.MODE_PRIVATE)
        gameMode = prefs.getInt("GAME_MODE", 0)

        // Set OnClickListener for the exit button
        exitButton.setOnClickListener {
            val broadcastIntent = Intent("RESET_SHARED_PREFS")
            requireActivity().sendBroadcast(broadcastIntent)

            requireActivity().finishAffinity() // Close the app
        }

        // Set OnClickListener for the profile button
        profileButton.setOnClickListener {
            when (gameMode) {
                1 -> {
                    val intent = Intent(requireActivity(), PlayerProfile1::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent1 = Intent(requireActivity(), PlayerProfile1::class.java)
                    startActivity(intent1)
                    val intent2 = Intent(requireActivity(), PlayerProfile2::class.java)
                    startActivity(intent2)
                }
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
