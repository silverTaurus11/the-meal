package com.project.meal.di

import com.project.meal.data.repository.MealRepository
import com.project.meal.di.NetworkModule
import com.project.meal.domain.repository.IMealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMealRepository(mealRepository: MealRepository): IMealRepository

}