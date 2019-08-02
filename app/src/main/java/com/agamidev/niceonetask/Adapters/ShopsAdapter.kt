package com.agamidev.niceonetask.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.agamidev.niceonetask.Activities.ShopDetailsActivity
import com.agamidev.niceonetask.Interfaces.CategoryShops
import com.agamidev.niceonetask.R
import com.agamidev.niceonetask.Models.ShopModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.card_shop_item.view.*

class ShopsAdapter() : RecyclerView.Adapter<ShopsAdapter.MyViewHolder>() {

    var context: Context? = null
    var shopsArrayList = ArrayList<ShopModel>()
    var catShopsInterface: CategoryShops? = null

    constructor(context: Context, shopsArrayList: ArrayList<ShopModel>, catShopsInterface: CategoryShops) : this() {
        this.context = context
        this.shopsArrayList = shopsArrayList
        this.catShopsInterface = catShopsInterface
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_shop_item, parent, false)

        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val shop = shopsArrayList[position]

        Glide.with(context!!)
                .load(shop.logo)
                .apply(
                        RequestOptions()
                                .placeholder(R.mipmap.placeholder_icon)
                                .fitCenter()
                )
                .into(holder.itemView.iv_shop_logo)

        holder.itemView.tv_shop_title.text = shop.title
        holder.itemView.tv_shop_phone.text = shop.primary_phone
        holder.itemView.tv_shop_addess.text = shop.address

        holder.itemView.setOnClickListener {
            val shop = shopsArrayList[holder.adapterPosition]
            var i = Intent(context,ShopDetailsActivity::class.java)
            i.putExtra("shop",shop)
            context!!.startActivity(i)


        }

    }

    override fun getItemCount() = shopsArrayList.size


    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
    }
}