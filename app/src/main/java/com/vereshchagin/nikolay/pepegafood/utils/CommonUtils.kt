package com.vereshchagin.nikolay.pepegafood.utils

import android.location.Address
import android.view.View

/**
 * Вспомогательные функции.
 */
class CommonUtils {

    companion object {
        /**
         * В зависимости от visible возвращает значение видимости для View.
         */
        fun toVisibly(visible: Boolean) = if (visible) View.VISIBLE else View.GONE

        fun formatAddress(address: Address): String? {
            return null
        }
    }
}