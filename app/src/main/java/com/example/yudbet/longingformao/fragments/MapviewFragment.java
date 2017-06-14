package com.example.yudbet.longingformao.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yudbet.longingformao.MainActivity;
import com.example.yudbet.longingformao.PetActivity;
import com.example.yudbet.longingformao.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class MapviewFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap mMap;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapview, container, false);
        context = view.getContext();

        initMapView(view, savedInstanceState);
        // Inflate the layout for this fragment
        return view;
    }

    public void initMapView(View view, Bundle savedInstanceState) {
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {

                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater(null).inflate(R.layout.info_window_pet, null);

                ImageView pet_image = (ImageView) view.findViewById(R.id.pet_image);
                TextView pet_info = (TextView) view.findViewById(R.id.pet_info);

                Picasso.with(getContext()).load(marker.getSnippet()).into(pet_image); // use url stored in snippet
                pet_info.setText(marker.getTitle());
                pet_info.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/cwTeXHei-zhonly.ttf"), Typeface.BOLD);

                return view;
            }
        });

        ArrayList<MarkerOptions> markers = MainActivity.getSampleMarkers();
        for (MarkerOptions marker : markers) {
            mMap.addMarker(marker);
        }

        LatLng TAIWAN = new LatLng(23.903687, 121.079370);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TAIWAN));
        mMap.setMinZoomPreference(7);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(context, PetActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
