package com.project.meal.view.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.project.meal.R
import com.project.meal.data.source.Resource
import com.project.meal.databinding.ActivityMainBinding
import com.project.meal.view.adapter.MealsAdapter
import com.project.meal.view.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mealsAdapter by lazy { MealsAdapter {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.ITEMS_KEY, it)
        })
    } }

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initSearchEditor()
        initSwipeRefresh()
    }

    private fun initRecyclerView(){
        binding.mealRecyclerView.adapter = mealsAdapter
        binding.mealRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initSearchEditor(){
        binding.searchContainer.searchEditor.clearFocus()
        binding.searchContainer.searchEditor.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch()
                true
            } else false
        }
        binding.searchContainer.searchButton.setOnClickListener {
            doSearch()
        }
    }

    private fun hideSoftKeyboard(){
        val inputMethodManager: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.windowToken?.apply {
            inputMethodManager.hideSoftInputFromWindow(this, 0)
        }
    }

    private fun doSearch(){
        val keyword = binding.searchContainer.searchEditor.text.toString()
        hideSoftKeyboard()
        searchMeal(keyword)
    }

    private fun searchMeal(query: String){
        if(query.isEmpty()) return
        mainViewModel.searchMeal(query).observe(this, {
            binding.loadingView.toVisible(it is Resource.Loading)
            binding.mealRecyclerView.toVisible(it is Resource.Success)
            binding.infoMessageView.toVisible(it is Resource.Error)
            when(it){
                is Resource.Success -> mealsAdapter.updateData(it.data)
                is Resource.Error ->{
                    val messageError = if(it.throwable is UnknownHostException){
                        getString(R.string.error_no_internet_connection_message)
                    }else{
                        it.message?: getString(R.string.error_cant_get_information)
                    }
                    Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show()
                    binding.infoMessageView.text = messageError
                }
                else -> return@observe
            }
        })
    }

    private fun View.toVisible(isVisible: Boolean){
        if(isVisible){
            this.visibility = View.VISIBLE
        } else{
            this.visibility = View.GONE
        }
    }

    private fun initSwipeRefresh(){
        binding.swipeRefresh.setOnRefreshListener {
            doSearch()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefresh.isRefreshing = false
            }, 500)
        }
    }
}