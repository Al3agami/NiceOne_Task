package com.agamidev.niceonetask.Activities

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.agamidev.niceonetask.Adapters.CategoriesAdapter
import com.agamidev.niceonetask.Adapters.ShopsAdapter
import com.agamidev.niceonetask.Contracts.MainContract
import com.agamidev.niceonetask.Interactors.CategoriesInteractorImp
import com.agamidev.niceonetask.Interactors.ShopsInteractorImp
import com.agamidev.niceonetask.Interfaces.CategoryShops
import com.agamidev.niceonetask.Models.CategoryModel
import com.agamidev.niceonetask.Models.ShopModel
import com.agamidev.niceonetask.Presenters.MainPresenter
import com.agamidev.niceonetask.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), CategoryShops, MainContract.MainView {

    var categoriesAdapter: CategoriesAdapter? = null
    var shopsAdapter: ShopsAdapter? = null

    private var presenter: MainContract.MainPresenter? = null
    var mainProgress: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRecycler()
        mainProgress = true
        checkNetworkToLoad()

        pullToRefresh.setOnRefreshListener {
            checkNetworkToLoad()
            pullToRefresh.isRefreshing = false
        }

    }

    fun checkNetworkToLoad() {
        if (isNetworkAvailable())
            loadScreenData()
        else {
            showError()
        }

    }

    private fun initializeRecycler() {
        val catLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, true)
        rv_cats.layoutManager = catLayoutManager

        val shopLayoutManager = LinearLayoutManager(this@MainActivity)
        rv_shops.layoutManager = shopLayoutManager
    }

    fun loadScreenData() {
        presenter = MainPresenter(this, CategoriesInteractorImp(), ShopsInteractorImp())
        showProgress()
        presenter!!.getCategories()
    }

    override fun showCategories(categoriesArrayList: ArrayList<CategoryModel>) {
        if (pullToRefresh.visibility == View.VISIBLE) {
            pullToRefresh.isEnabled = false
            pullToRefresh.visibility = View.GONE
        }
        categoriesAdapter = CategoriesAdapter(this, categoriesArrayList, this)
        rv_cats.adapter = categoriesAdapter
    }

    override fun getCatShops(id: String) {
        showProgress()
        if (isNetworkAvailable())
            presenter!!.getShopsByCatId(id)
        else
            showError()
    }


    override fun showShops(shopsArrayList: ArrayList<ShopModel>) {
        shopsAdapter = ShopsAdapter(this, shopsArrayList, this)
        rv_shops.adapter = shopsAdapter
    }

    override fun showProgress() {
        if (mainProgress!!) {
            main_progress_bar!!.visibility = View.VISIBLE
            mainProgress = false
        } else {
            shops_progress_bar!!.visibility = View.VISIBLE
        }

    }

    override fun hideProgress() {
        if (main_progress_bar!!.visibility == View.VISIBLE) {
            main_progress_bar!!.visibility = View.GONE
        } else
            shops_progress_bar!!.visibility = View.GONE
    }

    override fun onResponseFailure(throwable: Throwable) {
        Toast.makeText(this, "Cannot load data from server, Try again later!", Toast.LENGTH_SHORT).show()
        Log.e("MainActivityFailure", throwable.message)
    }

    override fun onDestroy() {
        presenter!!.onDestroy()
        super.onDestroy()
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    fun showError() {
        pullToRefresh.visibility = View.VISIBLE
        pullToRefresh.isEnabled = true
    }
}
