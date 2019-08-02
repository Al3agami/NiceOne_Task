package com.agamidev.niceonetask.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.agamidev.niceonetask.Models.CategoryModel
import com.agamidev.niceonetask.Interfaces.CategoryShops
import com.agamidev.niceonetask.MyGlideApp
import com.agamidev.niceonetask.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.card_category_item.view.*

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {


    var context:Context?=null
    var categoriesArray = ArrayList<CategoryModel>()
    var cat_index:Int? = null
    var catShopsInterface: CategoryShops? = null

    constructor(context: Context, categoriesArray: ArrayList<CategoryModel>, catShopsInterface: CategoryShops) : this(){
        this.context = context
        this.categoriesArray = categoriesArray
        this.catShopsInterface = catShopsInterface
        cat_index = 0
        catShopsInterface.getCatShops("all")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_category_item, parent, false)
        return MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val cat = categoriesArray[position]

        holder.itemView.tv_cat_name.text = cat.title

        Glide.with(context!!)
            .load(cat.logo)
            .apply(
                RequestOptions()
                    .placeholder(R.mipmap.placeholder_icon)
                    .fitCenter()
            )
            .into(holder.itemView.iv_cat_logo)

        holder.itemView.setOnClickListener {
            val clickedCat = categoriesArray[holder.adapterPosition]
            cat_index = holder.adapterPosition
            notifyDataSetChanged()
            catShopsInterface!!.getCatShops(clickedCat.id)
        }

        if (cat_index == position){
            holder.itemView.iv_cat_logo.background = context!!.resources.getDrawable(R.drawable.selected_category_circle)
            holder.itemView.tv_cat_name.setTextColor(context!!.resources.getColor(R.color.colorPrimary))
        }else {
            holder.itemView.iv_cat_logo.background = context!!.resources.getDrawable(R.drawable.simple_category_circle)
            holder.itemView.tv_cat_name.setTextColor(context!!.resources.getColor(R.color.lightGray))
        }




    }

    override fun getItemCount() = categoriesArray.size

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){
        private var view: View = v
    }

}