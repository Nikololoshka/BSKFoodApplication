package com.vereshchagin.nikolay.pepegafood.home

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.vereshchagin.nikolay.pepegafood.home.repository.HomeRepository
import com.vereshchagin.nikolay.pepegafood.profile.login.LoginViewModel
import com.vereshchagin.nikolay.pepegafood.profile.login.repository.LoginRepository

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val repositoryListing = MutableLiveData(repository.content())
    val shoppingBaskets = Transformations.switchMap(repositoryListing) { it.shoppingBaskets }
    val favoriteBaskets = Transformations.switchMap(repositoryListing) { it.favoriteBaskets }
    val catalogItems = Transformations.switchMap(repositoryListing) { it.catalogItems }

    class Factory (
        private val application: Application
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(
                    HomeRepository(application)
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}