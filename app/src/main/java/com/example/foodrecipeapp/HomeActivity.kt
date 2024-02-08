package com.example.foodrecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodrecipeapp.adapter.MainCategoryAdapter
import com.example.foodrecipeapp.adapter.SubCategoryAdapter
import com.example.foodrecipeapp.database.RecipesDatabase
import com.example.foodrecipeapp.databinding.ActivityHomeBinding
import com.example.foodrecipeapp.entity.CategoryItems
import com.example.foodrecipeapp.entity.MealsItem
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.EasyPermissions

class HomeActivity : BaseActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var mainCategoryAdapter: MainCategoryAdapter
    lateinit var subCategoryAdapter: SubCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCategoriesFromDB()

        setupViews()

    }

    private fun setupViews() {
        mainCategoryAdapter = MainCategoryAdapter(listOf())
        subCategoryAdapter = SubCategoryAdapter(listOf())

        binding.rvMainCategory.adapter = mainCategoryAdapter
        binding.rvSubCategory.adapter = subCategoryAdapter

        mainCategoryAdapter.onItemClickListener = object :MainCategoryAdapter.OnItemClickListener{
            override fun onItemClick(categoryName: String) {
                getSpecificMealsFromDB(categoryName)
            }

        }

    }

    fun getCategoriesFromDB() {
        launch {
            this.let {
                var categories = RecipesDatabase
                    .getRecipesDBInstance(this@HomeActivity)
                    .getRecipesDao()
                    .getAllCategories()

                getSpecificMealsFromDB((categories as ArrayList<CategoryItems>)[0].strcategory!!)

                mainCategoryAdapter.setMainCategories(categories as ArrayList<CategoryItems>)
            }
        }
    }

    fun getSpecificMealsFromDB(categoryName:String){
        binding.tvCategory.text = "$categoryName category"
        launch {
            this.let {
                val meals = RecipesDatabase
                    .getRecipesDBInstance(this@HomeActivity)
                    .getRecipesDao()
                    .getSpecificMealsList(categoryName)

                subCategoryAdapter.setMealsData(meals as ArrayList<MealsItem>)
            }
        }
    }
}

