package com.vereshchagin.nikolay.pepegafood.settings

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.android.gms.maps.model.LatLng

class ApplicationPreference {

    companion object {

        private const val USER_ADDRESS = "user_address"
        private const val USER_ADDRESS_COORDINATES = "user_address_coordinates"

        fun userAddress(context: Context): String? {
            return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(USER_ADDRESS, null)
        }

        fun setUserAddress(context: Context, address: String) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(USER_ADDRESS, address)
                .apply()
        }

        fun setUserAddressCoordinates(context: Context, latitude: Double, longitude: Double) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(USER_ADDRESS_COORDINATES, "$latitude;$longitude")
                .apply()
        }

        fun userAddressCoordinates(context: Context?): LatLng {
            return if (context == null) {
                LatLng(0.0, 0.0)
            } else {
                val list = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(USER_ADDRESS_COORDINATES, "0;0")!!.split(";")

                LatLng(list[0].toDouble(), list[1].toDouble())
            }
        }
    }
}