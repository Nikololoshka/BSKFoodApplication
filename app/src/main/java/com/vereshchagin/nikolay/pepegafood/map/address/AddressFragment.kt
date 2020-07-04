package com.vereshchagin.nikolay.pepegafood.map.address

import android.Manifest
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.vereshchagin.nikolay.pepegafood.MainActivity
import com.vereshchagin.nikolay.pepegafood.R
import com.vereshchagin.nikolay.pepegafood.databinding.FragmentAddressBinding
import com.vereshchagin.nikolay.pepegafood.settings.ApplicationPreference

/**
 * Фрагмент для смены адреса пользователя для доставки.
 */
class AddressFragment : Fragment() {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    /**
     * Google карта.
     */
    private var map: GoogleMap? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Callback для карты
        val mapFragment = childFragmentManager.findFragmentById(R.id.address_map) as SupportMapFragment?
        mapFragment?.getMapAsync { map ->
            this.map = map
            map.setOnCameraIdleListener {
                Log.d("MyLog", "onViewCreated: " + map.cameraPosition.target)
            }

            checkLocationPermission()
            updateMapUI()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateMapUI()
                return
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun updateMapUI() {
        val coordinates = ApplicationPreference.userAddressCoordinates(context)

        map?.let { map ->
            map.addMarker(
                MarkerOptions()
                    .position(coordinates)
                    .title("Marker in Sydney")
                    .snippet("Move marker")
                    .draggable(true)
            )

            if (checkLocationPermission()) {
                map.isMyLocationEnabled = true
                map.uiSettings.isMyLocationButtonEnabled = true
            }
            map.uiSettings.isZoomControlsEnabled = true

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15.0F))
        }
    }

    /**
     * Проверяет, если разрешение на доступ к GPS.
     * Если нет, то запрашивает его.
     */
    private fun checkLocationPermission(): Boolean {
        context?.let { context ->
            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            ) {
                return true
            }
        }

        // запрос разрешения
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), LOCATION_PERMISSION_REQUEST_CODE
        )
        return false
    }
}