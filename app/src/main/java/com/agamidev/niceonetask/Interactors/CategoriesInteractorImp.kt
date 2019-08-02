package com.agamidev.niceonetask.Interactors

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.agamidev.niceonetask.Models.CategoryModel
import com.agamidev.niceonetask.Contracts.MainContract
import com.agamidev.niceonetask.WebServices.RetrofitWeb
import com.agamidev.niceonetask.WebServices.ServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesInteractorImp : MainContract.CategoriesInteractor {


    override fun getCategoriesFromServer(onFinishListener: MainContract.CategoriesInteractor.OnFinishListener) {


        RetrofitWeb.client.create(ServiceApi::class.java).getCategories().enqueue(object : Callback<ArrayList<CategoryModel>> {
            override fun onResponse(call: Call<ArrayList<CategoryModel>>, response: Response<ArrayList<CategoryModel>>) {
                if (response.body() != null) {
                    Log.e("getCatsStatus", "success")
                    onFinishListener.onCategoriesLoadingSuccess(response.body()!!)
                } else {
                    Log.e("getCatsStatus", "Body is Null")
                }
            }

            override fun onFailure(call: Call<ArrayList<CategoryModel>>, t: Throwable) {
                Log.e("getCatsStatus", t.toString())
                onFinishListener.onCategoriesLoadingFailed(t)
            }
        })


    }

}