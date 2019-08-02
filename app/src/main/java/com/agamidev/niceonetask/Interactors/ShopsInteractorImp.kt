package com.agamidev.niceonetask.Interactors

import android.util.Log
import com.agamidev.niceonetask.Contracts.MainContract
import com.agamidev.niceonetask.Models.ShopsResponseModel
import com.agamidev.niceonetask.WebServices.RetrofitWeb
import com.agamidev.niceonetask.WebServices.ServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopsInteractorImp : MainContract.ShopsInteractor {

    override fun getShopsFromServer(onFinishListener: MainContract.ShopsInteractor.OnFinishListener, cat_id: String) {


        when (cat_id){
            "all" -> getAllShops(onFinishListener)
            "1" -> getElectricalShops(onFinishListener)
            "2" -> getMechanicalShops(onFinishListener)
            "3" -> getSamkaryShops(onFinishListener)
            "4" -> getDehanShops(onFinishListener)
        }

    }


    fun getAllShops(onFinishListener: MainContract.ShopsInteractor.OnFinishListener){

        RetrofitWeb.client.create(ServiceApi::class.java).getAllShops().enqueue(object : Callback<ShopsResponseModel> {
            override fun onResponse(call: Call<ShopsResponseModel>, response: Response<ShopsResponseModel>) {
                if (response.body() != null) {
                    Log.e("getAllShopsStatus","success")
                    if (response.body()!!.data.size>0){
                        onFinishListener.onShopsLoadingSuccess(response.body()!!.data)
                        for (s in response.body()!!.data){
                            Log.e("getAllShopsStatus",s.title)
                        }
                    }
                } else {
                    Log.e("getAllShopsStatus", "Body is Null")
                }
            }

            override fun onFailure(call: Call<ShopsResponseModel>, t: Throwable) {
                Log.e("getAllShopsStatus", t.toString())
                onFinishListener.onShopsLoadingFailed(t)
            }
        })

    }

    fun getElectricalShops(onFinishListener: MainContract.ShopsInteractor.OnFinishListener){
        RetrofitWeb.client.create(ServiceApi::class.java).getElectricalShops().enqueue(object : Callback<ShopsResponseModel> {
            override fun onResponse(call: Call<ShopsResponseModel>, response: Response<ShopsResponseModel>) {
                if (response.body() != null) {
                    Log.e("getElectricalStatus","success")
                    if (response.body()!!.data.size>0){
                        onFinishListener.onShopsLoadingSuccess(response.body()!!.data)
                        for (s in response.body()!!.data){
                            Log.e("getElectricalStatus",s.title)
                        }
                    }
                } else {
                    Log.e("getElectricalStatus", "Body is Null")
                }
            }

            override fun onFailure(call: Call<ShopsResponseModel>, t: Throwable) {
                Log.e("getElectricalStatus", t.toString())
                onFinishListener.onShopsLoadingFailed(t)
            }
        })
    }

    fun getMechanicalShops(onFinishListener: MainContract.ShopsInteractor.OnFinishListener){
        RetrofitWeb.client.create(ServiceApi::class.java).getMechanicalShops().enqueue(object : Callback<ShopsResponseModel> {
            override fun onResponse(call: Call<ShopsResponseModel>, response: Response<ShopsResponseModel>) {
                if (response.body() != null) {
                    Log.e("getMechanicalStatus","success")
                    if (response.body()!!.data.size>0){
                        onFinishListener.onShopsLoadingSuccess(response.body()!!.data)
                        for (s in response.body()!!.data){
                            Log.e("getMechanicalStatus",s.title)
                        }
                    }
                } else {
                    Log.e("getMechanicalStatus", "Body is Null")
                }
            }

            override fun onFailure(call: Call<ShopsResponseModel>, t: Throwable) {
                Log.e("getMechanicalStatus", t.toString())
                onFinishListener.onShopsLoadingFailed(t)
            }
        })
    }

    fun getSamkaryShops(onFinishListener: MainContract.ShopsInteractor.OnFinishListener){
        RetrofitWeb.client.create(ServiceApi::class.java).getSamkaryShops().enqueue(object : Callback<ShopsResponseModel> {
            override fun onResponse(call: Call<ShopsResponseModel>, response: Response<ShopsResponseModel>) {
                if (response.body() != null) {
                    Log.e("getSamkaryStatus","success")
                    if (response.body()!!.data.size>0){
                        onFinishListener.onShopsLoadingSuccess(response.body()!!.data)
                        for (s in response.body()!!.data){
                            Log.e("getSamkaryStatus",s.title)
                        }
                    }
                } else {
                    Log.e("getSamkaryStatus", "Body is Null")
                }
            }

            override fun onFailure(call: Call<ShopsResponseModel>, t: Throwable) {
                Log.e("getSamkaryStatus", t.toString())
                onFinishListener.onShopsLoadingFailed(t)
            }
        })
    }

    fun getDehanShops(onFinishListener: MainContract.ShopsInteractor.OnFinishListener){
        RetrofitWeb.client.create(ServiceApi::class.java).getDehanShops().enqueue(object : Callback<ShopsResponseModel> {
            override fun onResponse(call: Call<ShopsResponseModel>, response: Response<ShopsResponseModel>) {
                if (response.body() != null) {
                    Log.e("getDehanStatus","success")
                    if (response.body()!!.data.size>0){
                        onFinishListener.onShopsLoadingSuccess(response.body()!!.data)
                        for (s in response.body()!!.data){
                            Log.e("getDehanStatus",s.title)
                        }
                    }
                } else {
                    Log.e("getDehanStatus", "Body is Null")
                }
            }

            override fun onFailure(call: Call<ShopsResponseModel>, t: Throwable) {
                Log.e("getDehanStatus", t.toString())
                onFinishListener.onShopsLoadingFailed(t)
            }
        })
    }

}