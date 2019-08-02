package com.agamidev.niceonetask.Contracts

import com.agamidev.niceonetask.Models.CategoryModel
import com.agamidev.niceonetask.Models.ShopModel

interface MainContract {


    interface MainPresenter {

        fun getCategories()

        fun getShopsByCatId(cat_id: String)

        fun onDestroy()
    }

    interface MainView{

        fun showProgress()

        fun hideProgress()

        fun showCategories(categoriesArrayList: ArrayList<CategoryModel>)

        fun onResponseFailure(throwable: Throwable)

        fun showShops(shopsArrayList: ArrayList<ShopModel>)

    }

    interface CategoriesInteractor{


        fun getCategoriesFromServer(onFinishListener: OnFinishListener)

        interface OnFinishListener{
            fun onCategoriesLoadingSuccess(categoriesArrayList: ArrayList<CategoryModel>)
            fun onCategoriesLoadingFailed(t: Throwable)
        }


    }

    interface ShopsInteractor{

        fun getShopsFromServer(onFinishListener: OnFinishListener, cat_id: String)

        interface OnFinishListener{
            fun onShopsLoadingSuccess(shopsArrayList: ArrayList<ShopModel>)
            fun onShopsLoadingFailed(t: Throwable)
        }


    }
}