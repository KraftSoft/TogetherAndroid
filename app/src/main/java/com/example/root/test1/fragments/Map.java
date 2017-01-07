package com.example.root.test1.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.test1.R;
import com.example.root.test1.responseObjects.Meeting;
import com.example.root.test1.serviceHelpers.ApiHelper;
import com.example.root.test1.serviceHelpers.NetworkResultReceiver;
import com.example.root.test1.utils.CircleTransform;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;


/**
 * Created by kic on 9/3/16.
 */

public class Map extends Fragment implements NetworkResultReceiver.Receiver {

    public MapView mMapView;
    public GoogleMap gmap;

    private String key;
    private ApiHelper apiHelper;
    private NetworkResultReceiver networkResultReceiver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        networkResultReceiver = new NetworkResultReceiver(new Handler());
        networkResultReceiver.setReceiver(this);

        key = getString(R.string.KEY);
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        gmap = mMapView.getMap();

        try {
            initMap(gmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        apiHelper = new ApiHelper(getActivity().getApplicationContext());
        apiHelper.getMeetingsList(networkResultReceiver);

        return rootView;
    }

    private void initMap(GoogleMap map) throws InterruptedException {

        final float msk_lat = (float) 55.75;
        final float msk_lng = (float) 37.61;
        final float initZoom = (float) 11.5;
        PicassoMarker target;

        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(msk_lat, msk_lng), initZoom));

        MarkerOptions markerOne = new MarkerOptions().position(new LatLng(msk_lat, msk_lng)).title("marker title");

        target = new PicassoMarker(map.addMarker(markerOne));

        Picasso.with(getContext()).
                load("https://pp.vk.me/c411420/v411420975/9f08/sFtni-s1a1o.jpg").
                resize(75, 75).
                centerCrop().
                transform(new CircleTransform()).
                into(target);

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
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        ArrayList<Meeting> meetingList = (ArrayList<Meeting>) resultData.getSerializable("meetingsList");
    }

    public class PicassoMarker implements Target {

        Marker mMarker;
        boolean isFinished = false;

        PicassoMarker(Marker marker) {
            mMarker = marker;
        }

        @Override
        public int hashCode() {
            return mMarker.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof PicassoMarker) {
                Marker marker = ((PicassoMarker) o).mMarker;
                return mMarker.equals(marker);
            } else {
                return false;
            }
        }

        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mMarker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
            isFinished = true;
        }

        public void onBitmapFailed(Drawable errorDrawable) {
            int a = 0;
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            int a = 0;
        }

    }

}