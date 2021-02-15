package com.project.meal.data.source.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealItem(
    @SerializedName("idMeal") val idMeal: String?="",
    @SerializedName("strMeal") val strMeal: String?= "",
    @SerializedName("strCategory") val strCategory: String?= "",
    @SerializedName("strArea") val strArea: String?= "",
    @SerializedName("strInstructions") val strInstruction: String?= "",
    @SerializedName("strMealThumb") val strMealThumb: String?= "",
    @SerializedName("strTags") val strTags: String?= ""
): Parcelable