package com.example.foodrecipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    private val TAG = "HomeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCategoriesFromDB()

        setupViews()

    }

    override fun onStart() {
        super.onStart()

        mainCategoryAdapter.onItemClickListener = object :MainCategoryAdapter.OnItemClickListener{
            override fun onItemClick(categoryName: String) {
                getSpecificMealsFromDB(categoryName)
            }

        }

        subCategoryAdapter.onSubItemClickListener = object : SubCategoryAdapter.OnSubItemClickListener{
            override fun onSubItemClick(id:String) {
                goToDetailsActivity(id)
            }

        }
    }

    private fun goToDetailsActivity(id:String) {
        // explicit intent
        var intent = Intent(this@HomeActivity,DetailsActivity::class.java)
        intent.putExtra(TAG,id)
        startActivity(intent)
    }

    private fun setupViews() {
        mainCategoryAdapter = MainCategoryAdapter(listOf())
        subCategoryAdapter = SubCategoryAdapter(listOf())

        binding.rvMainCategory.adapter = mainCategoryAdapter
        binding.rvSubCategory.adapter = subCategoryAdapter

    }

    fun getCategoriesFromDB() {
        launch {
            this.let {
                var categories = RecipesDatabase
                    .getRecipesDBInstance(this@HomeActivity)
                    .getRecipesDao()
                    .getAllCategories()

                var categoriesList = categories as ArrayList<CategoryItems>
                Toast.makeText(this@HomeActivity,"size is ${categoriesList.size}",Toast.LENGTH_SHORT).show()
                getSpecificMealsFromDB(categoriesList[0].strcategory)
                mainCategoryAdapter.setMainCategories(categoriesList)
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

