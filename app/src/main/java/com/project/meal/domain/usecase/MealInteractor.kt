package com.project.meal.domain.usecase

import androidx.lifecycle.LiveData
import com.project.meal.data.source.Resource
import com.project.meal.data.source.remote.model.MealItem
import com.project.meal.domain.repository.IMealRepository
import javax.inject.Inject

class MealInteractor @Inject constructor(private val mealRepository: IMealRepository): IMealUseCase {

    override fun searchMeals(query: String): LiveData<Resource<List<MealItem>>> {
        return mealRepository.searchMeals(query)
    }
}