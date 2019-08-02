package com.agamidev.niceonetask.WebServices

import com.agamidev.niceonetask.Models.CategoryModel
import com.agamidev.niceonetask.Models.ShopsResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {

    @GET("specialities")
    fun getCategories(): Call<ArrayList<CategoryModel>>


    @GET("search/all")
    fun getAllShops(): Call<ShopsResponseModel>

    @GET("search/1")
    fun getElectricalShops(): Call<ShopsResponseModel>

    @GET("search/2")
    fun getMechanicalShops(): Call<ShopsResponseModel>

    @GET("search/3")
    fun getSamkaryShops(): Call<ShopsResponseModel>

    @GET("search/4")
    fun getDehanShops(): Call<ShopsResponseModel>


}