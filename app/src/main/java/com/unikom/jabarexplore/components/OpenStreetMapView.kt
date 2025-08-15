package com.unikom.jabarexplore.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.maplibre.android.MapLibre
import org.maplibre.android.WellKnownTileServer
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng


@Composable
fun OpenStreetMapView(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context: Context ->
            // Inisialisasi MapLibre sekali di sini
            MapLibre.getInstance(
                context.applicationContext,
                "", // kosong karena kita pakai style dari URL
                WellKnownTileServer.MapLibre
            )

            val mapView = MapView(context)
            mapView.getMapAsync { map ->
                map.setStyle(
                    Style.Builder().fromUri("https://demotiles.maplibre.org/style.json")
                ) {
                    map.cameraPosition = CameraPosition.Builder()
                        .target(LatLng(-6.9175, 107.6191)) // Bandung
                        .zoom(12.0)
                        .build()
                }
            }
            mapView
        },
        modifier = modifier
    )
}