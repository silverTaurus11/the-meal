package com.project.meal.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class MealResponse (@SerializedName("meals") val meals: List<MealItem>)