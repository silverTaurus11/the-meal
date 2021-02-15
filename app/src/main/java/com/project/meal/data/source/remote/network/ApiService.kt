package com.project.meal.data.source.remote.network

import com.project.meal.data.source.remote.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

@JvmSuppressWildcards
interface ApiService {
    @GET("search.php")
    suspend fun searchMeal(@Query("f") query: String): MealResponse
}