package com.vereshchagin.nikolay.pepegafood.settings

import android.content.Context
import androidx.preference.PreferenceManager

class ApplicationPreference {

    companion object {

        private const val USER_ADDRESS = "user_address"

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
    }
}