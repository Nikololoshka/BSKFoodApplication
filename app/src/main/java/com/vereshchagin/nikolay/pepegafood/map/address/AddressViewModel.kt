package com.vereshchagin.nikolay.pepegafood.map.address

import android.app.Application
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.vereshchagin.nikolay.pepegafood.settings.ApplicationPreference
import com.vereshchagin.nikolay.pepegafood.utils.CommonUtils
import com.vereshchagin.nikolay.pepegafood.utils.LoadState
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * ViewModel для установления адреса доставки.
 */
class AddressViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Текущие состояние загрузки адреса.
     */
    val state = MutableLiveData(LoadState.LOADING)

    /**
     * Координаты на карте.
     */
    var coordinates = ApplicationPreference.userAddressCoordinates(application)

    /**
     * Текущий адрес.
     */
    var currentAddress = ApplicationPreference.userAddress(application)

    /**
     * Геокодер для адреса.
     */
    private val coder = Geocoder(application, Locale.getDefault())

    /**
     * Executor для получения адреса.
     */
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    /**
     * Обновляет адресс исходя из координат.
     */
    fun updateUserAddress(target: LatLng?) {
        if (target == null) {
            state.value = LoadState.EMPTY_ERROR
            return
        }

        executor.execute {
            state.postValue(LoadState.LOADING)

            val location = coder.getFromLocation(target.latitude, target.longitude, 1)
            if (location == null || location.isEmpty()) {
                state.postValue(LoadState.EMPTY_ERROR)
                return@execute
            }

            val address = location[0]
            currentAddress = CommonUtils.addressToString(address)
            coordinates = target

            state.postValue(LoadState.LOADED)
        }
    }

    /**
     * Factory для создания ViewModel.
     */
    class Factory(private val application: Application) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddressViewModel(application) as T
        }
    }
}