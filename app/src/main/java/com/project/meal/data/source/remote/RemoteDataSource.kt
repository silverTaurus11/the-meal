package com.project.meal.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.project.meal.data.source.remote.model.MealResponse
import com.project.meal.data.source.remote.network.ApiResponse
import com.project.meal.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun searchMeals(query: String): LiveData<ApiResponse<MealResponse>>{
        return flow {
            try {
                val response = apiService.searchMeal(query)
                if (response.meals.isNullOrEmpty()){
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO).asLiveData()
    }
}