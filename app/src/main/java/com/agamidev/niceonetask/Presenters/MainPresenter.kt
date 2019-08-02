package com.agamidev.niceonetask.Presenters

import com.agamidev.niceonetask.Models.CategoryModel
import com.agamidev.niceonetask.Contracts.MainContract
import com.agamidev.niceonetask.Models.ShopModel

class MainPresenter : MainContract.MainPresenter, MainContract.CategoriesInteractor.OnFinishListener, MainContract.ShopsInteractor.OnFinishListener {

    var mainView: MainContract.MainView?=null
    var categoriesInteractor: MainContract.CategoriesInteractor?=null
    var shopsInteractor: MainContract.ShopsInteractor?=null

    constructor(mainView: MainContract.MainView, categoriesInteractor: MainContract.CategoriesInteractor, shopsInteractor: MainContract.ShopsInteractor) {
        this.mainView = mainView
        this.categoriesInteractor = categoriesInteractor
        this.shopsInteractor = shopsInteractor
    }

    //Categories Functions
    override fun getCategories() {
        categoriesInteractor!!.getCategoriesFromServer(this)
    }

    override fun onCategoriesLoadingSuccess(categoriesArrayList: ArrayList<CategoryModel>) {
        mainView!!.hideProgress()
        mainView!!.showCategories(categoriesArrayList)
    }

    override fun onCategoriesLoadingFailed(t: Throwable) {
        mainView!!.hideProgress()
        mainView!!.onResponseFailure(t)
    }



    //Shops Functions
    override fun getShopsByCatId(id: String) {
        shopsInteractor!!.getShopsFromServer(this, id)
    }

    override fun onShopsLoadingSuccess(shopsArrayList: ArrayList<ShopModel>) {
        mainView!!.hideProgress()
        mainView!!.showShops(shopsArrayList)
    }

    override fun onShopsLoadingFailed(t: Throwable) {
        mainView!!.hideProgress()
        mainView!!.onResponseFailure(t)
    }


    //OnMainActivityDestroy
    override fun onDestroy() {
        mainView = null
        categoriesInteractor = null
        shopsInteractor = null
    }

}