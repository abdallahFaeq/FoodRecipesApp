package com.example.foodrecipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.databinding.ItemRvMainCategoryBinding
import com.example.foodrecipeapp.databinding.ItemRvSubCategoryBinding
import com.example.foodrecipeapp.entity.MealsItem
import com.example.foodrecipeapp.entity.Recipes

class SubCategoryAdapter(var subRecipes:List<MealsItem>):Adapter<SubCategoryAdapter.SubCategoryHolder>() {
    private var context:Context?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryHolder {
        this.context = parent.context
        return SubCategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sub_category,parent,false))
    }

    override fun getItemCount(): Int = subRecipes.size


    override fun onBindViewHolder(holder: SubCategoryHolder, position: Int) {
        var mainRecipe = subRecipes[holder.adapterPosition]
        holder.binding.tvDishName.text = mainRecipe.strMeal
        Glide.with(this.context!!).load(mainRecipe.strMealThumb).into(holder.binding.imgDish)

        if (onSubItemClickListener != null){
            holder.itemView.setOnClickListener({
                onSubItemClickListener?.onSubItemClick(mainRecipe.idMeal)
            })
        }
    }

    fun setMealsData(newMeals:List<MealsItem>){
        this.subRecipes = newMeals
        notifyDataSetChanged()
    }

    var onSubItemClickListener : OnSubItemClickListener? =null
    interface OnSubItemClickListener{
        fun onSubItemClick(id:String)
    }
    class SubCategoryHolder(itemView:View):ViewHolder(itemView){
        var binding = ItemRvSubCategoryBinding.bind(itemView)
    }
}