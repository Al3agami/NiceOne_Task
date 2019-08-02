package com.agamidev.niceonetask.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ShopModel(var id: Int, var title: String, var logo: String, var primary_phone:String, var secondry_phone: String,
                     var third_phone: String, var address: String, var lat: String, var lon: String, var online_checkup: Int,
                     var technician_request: Int, var repare_request: Int, var towing_request: Int, var appointment_request: Int,
                     var specialty_id: Int, var owner_id: Int, var created_at:Date?, var updated_at:Date?) : Parcelable