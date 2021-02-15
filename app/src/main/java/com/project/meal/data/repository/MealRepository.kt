package com.project.meal.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.meal.data.AppExecutors
import com.project.meal.data.OnlyNetworkBoundResourceLiveData
import com.project.meal.data.source.Resource
import com.project.meal.data.source.remote.RemoteDataSource
import com.project.meal.data.source.remote.model.MealItem
import com.project.meal.data.source.remote.model.MealResponse
import com.project.meal.data.source.remote.network.ApiResponse
import com.project.meal.domain.repository.IMealRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepository @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                         private val appExecutors: AppExecutors): IMealRepository {

    override fun searchMeals(query: String): LiveData<Resource<List<MealItem>>> {
        return object : OnlyNetworkBoundResourceLiveData<List<MealItem>, MealResponse>(appExecutors){
            override fun createCall(): LiveData<ApiResponse<MealResponse>> {
                return remoteDataSource.searchMeals(query)
            }

            override fun convertToResultType(remoteData: MealResponse): LiveData<List<MealItem>> {
                return MutableLiveData(remoteData.meals)
            }

        }.asLiveData()
    }
}