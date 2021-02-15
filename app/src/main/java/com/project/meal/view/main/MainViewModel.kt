package com.project.meal.view.main

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.project.meal.domain.usecase.IMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: IMealUseCase): ViewModel(),
    LifecycleObserver {
        fun searchMeal(query: String) = useCase.searchMeals(query)
}