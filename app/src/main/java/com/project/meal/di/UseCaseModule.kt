package com.project.meal.di

import com.project.meal.domain.usecase.IMealUseCase
import com.project.meal.domain.usecase.MealInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun provideMealUseCase(mealInteractor: MealInteractor): IMealUseCase

}