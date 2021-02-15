package com.project.meal.domain.usecase

import androidx.lifecycle.LiveData
import com.project.meal.data.source.Resource
import com.project.meal.data.source.remote.model.MealItem

interface IMealUseCase {
    fun searchMeals(query: String): LiveData<Resource<List<MealItem>>>
}