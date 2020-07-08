package com.vereshchagin.nikolay.pepegafood.basket

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.vereshchagin.nikolay.pepegafood.R
import com.vereshchagin.nikolay.pepegafood.databinding.FragmentBasketBinding

/**
 * Фрагмент с корзиной.
 */
class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)

        val sheetBehavior = BottomSheetBehavior.from(binding.favoriteBaskets)
        sheetBehavior.isFitToContents = false
        sheetBehavior.isHideable = false
        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        sheetBehavior.setExpandedOffset(100)

        sheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                binding.favoriteBasketsButton.setImageDrawable(
                    context?.getDrawable(
                        if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                            R.drawable.ic_common_arrow_up
                        } else {
                            R.drawable.ic_common_arrow_down
                        }
                    )
                )
            }
        })

        binding.favoriteBasketsButton.setOnClickListener {
            sheetBehavior.state = if (sheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                BottomSheetBehavior.STATE_HALF_EXPANDED
            } else {
                BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)
            .get(BasketViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}