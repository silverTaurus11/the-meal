package com.project.meal.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.meal.data.source.remote.model.MealItem
import com.project.meal.databinding.SearchMealItemBinding

class MealsAdapter(private val callBack: (MealItem)-> Unit): RecyclerView.Adapter<MealsAdapter.MealViewHolder>(){
    private val meals: MutableList<MealItem> = mutableListOf()

    fun updateData(meals: List<MealItem>?){
        if(meals == null) return
        this.meals.clear()
        this.meals.addAll(meals)
        notifyDataSetChanged()
    }

    class MealViewHolder(val binding: SearchMealItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = SearchMealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val item = meals[position]
        holder.binding.mealImage.apply {
            Glide.with(this).load(item.strMealThumb).centerCrop().into(this)
        }
        holder.binding.mealName.text = item.strMeal
        holder.itemView.setOnClickListener {
            callBack(item)
        }
    }

    override fun getItemCount(): Int = meals.size
}