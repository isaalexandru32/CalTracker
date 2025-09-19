package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: foodAdapter
    private val foodList = mutableListOf<food_item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.foodList)
        val btAddFood: Button = findViewById(R.id.btAddFood)

        adapter = foodAdapter(foodList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btAddFood.setOnClickListener {
            showAddFoodDialog()
        }

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    private fun showAddFoodDialog(){
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_food, null)

        val etTitle = dialogView.findViewById<EditText>(R.id.etTitle)
        val etCal = dialogView.findViewById<EditText>(R.id.etCal)
        val etProt = dialogView.findViewById<EditText>(R.id.etProt)
        val etFats = dialogView.findViewById<EditText>(R.id.etFats)
        val etCarbs = dialogView.findViewById<EditText>(R.id.etCarbs)

        AlertDialog.Builder(this)
            .setTitle("Add an element")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val newFood = food_item(
                    title = etTitle.text.toString(),
                    cal = etCal.text.toString(),
                    prot = etProt.text.toString(),
                    fats = etFats.text.toString(),
                    carbs = etCarbs.text.toString()
                )

                foodList.add(newFood)

                adapter.notifyItemInserted(foodList.size - 1)
                recyclerView.scrollToPosition(foodList.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

}

