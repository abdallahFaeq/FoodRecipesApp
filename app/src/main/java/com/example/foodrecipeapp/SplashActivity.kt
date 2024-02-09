package com.example.foodrecipeapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.foodrecipeapp.database.RecipesDatabase
import com.example.foodrecipeapp.databinding.ActivitySplashBinding
import com.example.foodrecipeapp.entity.Category
import com.example.foodrecipeapp.entity.CategoryItems
import com.example.foodrecipeapp.entity.Meal
import com.example.foodrecipeapp.entity.MealsItem
import com.example.foodrecipeapp.retrofitclients.RetrofitClientInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity(),EasyPermissions.PermissionCallbacks {
    companion object{
        const val READ_STORAGE_REQUEST_CODE = 1
    }

    private val TAG = "SplashActivity"
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readStorageTask()

        goToHomeActivity()
    }

    private fun goToHomeActivity() {
        // when click button get started
        binding.buttonGetStarted.setOnClickListener({
            startHomeActivity()
        })
    }

    private fun startHomeActivity() {
        startActivity(Intent(this@SplashActivity,HomeActivity::class.java))
        finish()
    }

    fun getCategories(){
        binding.buttonGetStarted.visibility = View.INVISIBLE
        binding.loader.visibility = View.VISIBLE
        val call = RetrofitClientInstance.categoryService.getCategoryList()
        call.enqueue(object :Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                for (arr in response.body()?.categorieitems!!){
                    getMeals(arr.strcategory)
                }
                insertDataIntoDB(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                binding.loader.visibility = View.GONE
                binding.buttonGetStarted.visibility = View.GONE
                Toast.makeText(this@SplashActivity,"something went wrong",Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun getMeals(categoryName:String?){
        val call = RetrofitClientInstance.categoryService.getMealItemsList(categoryName)
        call.enqueue(object :Callback<Meal>{
            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                insertMealDataIntoDB(categoryName,response.body())
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                binding.loader.visibility = View.GONE
                Toast.makeText(this@SplashActivity,"something went wrong",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun insertDataIntoDB(categories:Category?) {
        launch {
            for (arr in categories?.categorieitems!!){
                RecipesDatabase.getRecipesDBInstance(this@SplashActivity)
                    .getRecipesDao()
                    .insertCategory(arr)

                Log.d(TAG, "insertDataIntoDB: $arr")
            }
            binding.buttonGetStarted.visibility = View.VISIBLE
        }
    }

    private fun insertMealDataIntoDB(categoryName: String?,meal:Meal?) {
        launch {
            for (arr in meal!!.meals!!){
                var mealItems = MealsItem(
                    0,
                    arr!!.strMeal,
                    arr!!.strMealThumb,
                    arr!!.idMeal,
                    categoryName!!
                )
                RecipesDatabase.getRecipesDBInstance(this@SplashActivity)
                    .getRecipesDao()
                    .insertMeal(mealItems)
            }
            binding.buttonGetStarted.visibility = View.VISIBLE
        }
    }

    fun clearDb(){
        launch {
            this.let {
                RecipesDatabase
                    .getRecipesDBInstance(this@SplashActivity)
                    .getRecipesDao()
                    .clearDB()
            }
        }
    }
    fun clearMealItems(){
        launch {
            this.let {
                RecipesDatabase
                    .getRecipesDBInstance(this@SplashActivity)
                    .getRecipesDao()
                    .clearMealItemDB()
            }
        }
    }
    fun readStorageTask(){
        if (checkPermission()){
            if (isConnectedToInternet()){
                clearDb()
                clearMealItems()
                getCategories()
            }else{
                binding.buttonGetStarted.visibility = View.VISIBLE
                binding.loader.visibility = View.GONE
            }
        }else{
            EasyPermissions.requestPermissions(
                this,
                "App needs access to your storage",
                READ_STORAGE_REQUEST_CODE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    // check if internet connected or not
    private fun isConnectedToInternet():Boolean {
        var connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            var network = connectivityManager.activeNetwork ?: return false
            var networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        }else{
            var networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

    fun checkPermission():Boolean{
        if (EasyPermissions.hasPermissions(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
            return true
        }else{
            return false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == READ_STORAGE_REQUEST_CODE){
            getCategories()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
       if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
           AppSettingsDialog.Builder(this).build().show()
       }else{
           EasyPermissions.requestPermissions(
               this,
               "App needs access to your storage",
               READ_STORAGE_REQUEST_CODE,
               android.Manifest.permission.READ_EXTERNAL_STORAGE)
       }
    }
}
