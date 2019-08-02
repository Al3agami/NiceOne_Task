package com.agamidev.niceonetask.Models


data class ShopsResponseModel(var current_page: Int, var data:ArrayList<ShopModel>, var first_page_url: String, var from: Int,
                              var last_page: Int, var last_page_url: String?, var next_page_url: String?, var path: String,
                              var per_page: Int, var prev_page_url: String?, var to: Int, var total: Int)