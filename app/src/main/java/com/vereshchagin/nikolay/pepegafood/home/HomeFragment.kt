package com.vereshchagin.nikolay.pepegafood.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vereshchagin.nikolay.pepegafood.databinding.FragmentHomeBinding
import com.vereshchagin.nikolay.pepegafood.home.paging.catalog.CatalogAdapter
import com.vereshchagin.nikolay.pepegafood.home.paging.favorite.FavoriteBasketAdapter
import com.vereshchagin.nikolay.pepegafood.home.paging.shopping.ShoppingBasketAdapter

/**
 *
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this,
            HomeViewModel.Factory(activity?.application!!))
            .get(HomeViewModel::class.java)

        val shoppingBasketAdapter = ShoppingBasketAdapter()
        binding.recyclerAutoBaskets.adapter = shoppingBasketAdapter

        viewModel.shoppingBaskets.observe(viewLifecycleOwner, Observer {
            shoppingBasketAdapter.submitList(it)
        })

        val favoriteBasketAdapter = FavoriteBasketAdapter()
        binding.recyclerFavorites.adapter = favoriteBasketAdapter

        viewModel.favoriteBaskets.observe(viewLifecycleOwner, Observer {
            favoriteBasketAdapter.submitList(it)
        })

        val catalogAdapter = CatalogAdapter()
        binding.recyclerCatalog.adapter = catalogAdapter

        viewModel.catalogItems.observe(viewLifecycleOwner, Observer {
            catalogAdapter.submitList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}