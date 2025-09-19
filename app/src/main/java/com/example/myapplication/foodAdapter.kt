package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
        val currentFood = foods[position]

        //add the values of food_item in the text view
        holder.tvitem_food.text = currentFood.title
        holder.tvitem_cal.text = currentFood.cal
        holder.tvitem_prot.text = currentFood.prot
        holder.tvitem_fats.text = currentFood.fats
        holder.tvitem_carb.text = currentFood.carbs

        holder.btDeleteFood.setOnClickListener {
            foods.removeAt(position)
            notifyItemRemoved(position) //update UI
        }

        holder.btModFood.setOnClickListener {
            //this is where we will modify the added food

            val context = holder.itemView.context
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_food, null)

            val etTitle = dialogView.findViewById<EditText>(R.id.etTitle)
            val etCal = dialogView.findViewById<EditText>(R.id.etCal)
            val etProt = dialogView.findViewById<EditText>(R.id.etProt)
            val etFats = dialogView.findViewById<EditText>(R.id.etFats)
            val etCarbs = dialogView.findViewById<EditText>(R.id.etCarbs)

            etTitle.setText(currentFood.title)
            etCal.setText(currentFood.cal)
            etProt.setText(currentFood.prot)
            etFats.setText(currentFood.fats)
            etCarbs.setText(currentFood.carbs)

            AlertDialog.Builder(context)
                .setTitle("Modify an item")
                .setView(dialogView)
                .setPositiveButton("Save") { _, _ ->
                    // create a new object with the updated values
                    val updatedFood = currentFood.copy(
                        title = etTitle.text.toString(),
                        cal = etCal.text.toString(),
                        prot = etProt.text.toString(),
                        fats = etFats.text.toString(),
                        carbs = etCarbs.text.toString()
                    )

                    foods[position] = updatedFood

                    notifyItemChanged(position)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun getItemCount(): Int {
        return foods.size
    }
}
