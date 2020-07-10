package com.vereshchagin.nikolay.pepegafood.ui.profile.viewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vereshchagin.nikolay.pepegafood.utils.LoadState

class ProfileViewModel : ViewModel() {

    val loadState = MutableLiveData(LoadState.LOADING)

}