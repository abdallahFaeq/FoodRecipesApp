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
import com.example.foodrecipeapp.entity.CategoryItems
import com.example.foodrecipeapp.entity.Recipes

class MainCategoryAdapter(var mainCategoryItems:List<CategoryItems>):Adapter<MainCategoryAdapter.MainCategoryHolder>() {
    private var context:Context?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoryHolder {
        this.context = parent.context
        return MainCategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_category,parent,false))
    }

    override fun getItemCount(): Int = mainCategoryItems.size


    override fun onBindViewHolder(holder: MainCategoryHolder, position: Int) {
        var mainCategoryItem = mainCategoryItems[holder.adapterPosition]
        holder.binding.tvDishName.text = mainCategoryItem.strcategory
        Glide.with(context!!).load(mainCategoryItem.strcategorythumb).into(holder.binding.imageDish)

        if (onItemClickListener != null){
            holder.binding.root.setOnClickListener({
                onItemClickListener!!.onItemClick(mainCategoryItem.strcategory)
            })
        }
    }
    fun setMainCategories(mainCategoryItems: List<CategoryItems>){
        this.mainCategoryItems = mainCategoryItems
        notifyDataSetChanged()
    }
    class MainCategoryHolder(itemView:View):ViewHolder(itemView){
        var binding = ItemRvMainCategoryBinding.bind(itemView)
    }

    var onItemClickListener:OnItemClickListener ?= null
    interface OnItemClickListener{
        fun onItemClick(categoryName:String)
    }
}