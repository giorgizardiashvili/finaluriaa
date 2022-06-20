package com.example.finaluri.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.finaluri.R
import com.google.android.material.button.MaterialButton


class HomeFragment : Fragment(){
    private lateinit var buttonProfile: MaterialButton
    private lateinit var buttonFood: MaterialButton
    private lateinit var buttonHistory: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        buttonProfile = view.findViewById(R.id.buttonProfile)
        buttonFood = view.findViewById(R.id.buttonFood)
        buttonHistory = view.findViewById(R.id.buttonHistory)

        buttonHistory.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_historyActivity)
        }
        buttonFood.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_foodsActivity)
        }
        buttonProfile.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_profileActivity)
        }
        return view
    }


}