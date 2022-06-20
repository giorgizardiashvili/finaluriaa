package com.example.finaluri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        findViewById<TextView>(R.id.textView9).text =
            intent.extras?.getString(FoodRecycleAdapter.FoodViewHolder.STORY)
    }
}