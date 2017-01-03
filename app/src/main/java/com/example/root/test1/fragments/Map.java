package com.example.root.test1.fragments;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.test1.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;


/**
 * Created by root on 9/3/16.
 */

public class Map extends SupportMapFragment {
    
    private String key = null;
    public GoogleMap gmap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        key = getString(R.string.KEY);
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        // mMapView.onResume(); // needed to get the map to display immediately

        gmap = getMapFragment().getMap();

        if (gmap == null) {
            return null;
        }

        return rootView;
    }

    private SupportMapFragment getMapFragment() {
        FragmentManager fm = null;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            fm = getFragmentManager();
        } else {
            fm = getChildFragmentManager();
        }

        return (SupportMapFragment) fm.findFragmentById(R.id.mapView);
    }


    @Override
    public void onStart() {
        super.onStart();
        //ServiceHelper serviceHelper = new ServiceHelper(getActivity().getApplicationContext());
        //serviceHelper.getUserAlbumsLink(currentUser);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

