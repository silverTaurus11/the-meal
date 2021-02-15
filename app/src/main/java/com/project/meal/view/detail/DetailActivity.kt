package com.project.meal.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.project.meal.data.source.remote.model.MealItem
import com.project.meal.databinding.ActivityDetailBinding

class DetailActivity: AppCompatActivity() {

    companion object{
        const val ITEMS_KEY = "itemsKey"
    }

    private lateinit var binding: ActivityDetailBinding

    private val items: MealItem? by lazy { intent.getParcelableExtra(ITEMS_KEY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backButton.setOnClickListener { onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        items?.let { setUpView(it) }
    }

    private fun setUpView(item: MealItem){
        binding.backdropImageView.apply {
            Glide.with(this@DetailActivity).load(item.strMealThumb).into(this)
        }
        binding.coverImageView.apply {
            Glide.with(this@DetailActivity).load(item.strMealThumb).into(this)
        }
        binding.categoryTextView.text = item.strCategory
        binding.areaTextView.text = item.strArea
        binding.detailTitleTextView.text = item.strMeal
        binding.tagsTextView.text = item.strTags
        binding.detailInstructionTextView.text = item.strInstruction
    }
}