package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class foodAdapter(
    private val foods: MutableList<food_item>
) : RecyclerView.Adapter<foodAdapter.foodViewHolder>() {
    class foodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvitem_food: TextView = itemView.findViewById(R.id.tvitem_food)
        val tvitem_cal: TextView = itemView.findViewById(R.id.tvitem_cal)
        val tvitem_prot: TextView = itemView.findViewById(R.id.tvitem_prot)
        val tvitem_fats: TextView = itemView.findViewById(R.id.tvitem_fats)
        val tvitem_carb: TextView = itemView.findViewById(R.id.tvitem_carb)
        val btDeleteFood: Button = itemView.findViewById(R.id.btDeleteFood)
        val btModFood: Button = itemView.findViewById(R.id.btModFood)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foodViewHolder {
        return foodViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.consumed_food,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: foodViewHolder, position: Int) {
        R.layout.consumed_food
    }

    override fun getItemCount(): Int {
        return foods.size
    }
}
