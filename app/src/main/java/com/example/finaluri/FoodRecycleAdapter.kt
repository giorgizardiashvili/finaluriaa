package com.example.finaluri

import android.app.Person
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FoodRecycleAdapter(private var foods: List<Food>): RecyclerView.Adapter<FoodRecycleAdapter.FoodViewHolder>(){
    class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private val imageView = itemView.findViewById<ImageView>(R.id.item_imageView)
        private val title = itemView.findViewById<TextView>(R.id.item_title)
        private val details = itemView.findViewById<TextView>(R.id.item_detail)

        private lateinit var foods: Food

        init {
            itemView.setOnClickListener(this)
        }

        companion object {
            const val STORY = "STORY"
        }

        fun onBind(foods: Food){
            this.foods = foods

            Glide.with(itemView)
                .load(foods.imgURL)
                .into(imageView)
            title.text = foods.name
            details.text = foods.story
        }

        override fun onClick(clickeView: View?) {
            val context = itemView.context
            val intent = Intent(context, FoodActivity::class.java)
            intent.putExtra(STORY, foods.story)
            context.startActivities(arrayOf(intent))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.onBind(foods[position])
    }

    override fun getItemCount() = foods.size


}