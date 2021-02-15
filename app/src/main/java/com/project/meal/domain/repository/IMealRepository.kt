package com.project.meal.domain.repository

import androidx.lifecycle.LiveData
import com.project.meal.data.source.Resource
import com.project.meal.data.source.remote.model.MealItem

interface IMealRepository {
    fun searchMeals(query: String): LiveData<Resource<List<MealItem>>>
}