package com.agamidev.niceonetask.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.agamidev.niceonetask.Models.ShopModel
import com.agamidev.niceonetask.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_shop_details.*
import kotlinx.android.synthetic.main.activity_shop_details.iv_shop_logo
import kotlinx.android.synthetic.main.activity_shop_details.tv_shop_title
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView

class ShopDetailsActivity : AppCompatActivity() {

    var shop: ShopModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)

        if (intent.getParcelableExtra<ShopModel>("shop") != null)
            shop = intent.getParcelableExtra("shop")

        fillView()


    }

    fun fillView() {
        Glide.with(this)
                .load(shop!!.logo)
                .apply(
                        RequestOptions()
                                .placeholder(R.mipmap.placeholder_icon)
                                .fitCenter()
                )
                .into(iv_shop_logo)

        tv_shop_title_header.text = shop!!.title

        tv_shop_title.text = shop!!.title

        if (shop!!.primary_phone.length > 0) {
            showView(iv_phone_icon_1, tv_phone_1, 1)
        }
        if (shop!!.secondry_phone.length > 0) {
            showView(iv_phone_icon_2, tv_phone_2, 2)
        }
        if (shop!!.primary_phone.length > 0) {
            showView(iv_phone_icon_3, tv_phone_3, 3)
        }

        if (shop!!.address.length > 0) {
            showView(iv_address_icon, tv_address, 4)
        }
    }

    fun showView(v1: ImageView, v2: TextView, no: Int) {
        v1.visibility = View.VISIBLE
        v2.visibility = View.VISIBLE
        when (no) {
            1 -> v2.text = shop!!.primary_phone
            2 -> v2.text = shop!!.secondry_phone
            3 -> v2.text = shop!!.third_phone
            4 -> v2.text = shop!!.address
        }
    }

    fun openOnMaps(v: View) {
        val lat = shop!!.lat
        val lng = shop!!.lon
        val geoUri = "http://maps.google.com/maps?q=loc:$lat,$lng (Title)"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
        startActivity(intent)
    }

    fun onCallNumber(v: View) {
        when (v) {
            tv_phone_1 -> callNumber(shop!!.primary_phone)
            tv_phone_2 -> callNumber(shop!!.secondry_phone)
            tv_phone_3 -> callNumber(shop!!.third_phone)
        }
    }

    fun callNumber(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + number)
        startActivity(intent)
    }

}
