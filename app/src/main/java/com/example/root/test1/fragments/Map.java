package com.example.root.test1.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.test1.R;
import com.example.root.test1.apiObjects.Meeting;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;


/**
 * Created by kic on 9/3/16.
 */

public class Map extends Fragment implements NetworkResultReceiver.Receiver {

    public static final int AVATAR_SIZE = 80;

    public MapView mMapView;
    public GoogleMap gmap;

    private String key;
    private ApiHelper apiHelper;
    private NetworkResultReceiver networkResultReceiver;
    private ArrayList<Meeting> meetingList;

    public ArrayList<PicassoMarker> getTargetsList() {
        return targetsList;
    }

    private ArrayList<PicassoMarker> targetsList;

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
            initMap();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        apiHelper = new ApiHelper(getActivity().getApplicationContext());
        apiHelper.getMeetingsList(networkResultReceiver);

        return rootView;
    }

    private void initMap() throws InterruptedException {

        final float msk_lat = (float) 55.75;
        final float msk_lng = (float) 37.61;
        final float initZoom = (float) 10.5;

        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(msk_lat, msk_lng), initZoom));

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

        meetingList = (ArrayList<Meeting>) resultData.getSerializable("meetingsList");

        PicassoMarker target;
        targetsList = new ArrayList<>();
        MarkerOptions markerOne;

        int cycle = 0;

        if (meetingList != null) {

            for (Meeting m: meetingList) {


                markerOne = new MarkerOptions().position(m.getCoordinates()).title("marker title");

                target = new PicassoMarker(gmap.addMarker(markerOne));
                targetsList.add(target);

                String avatar = "";

                try {
                    avatar = URLDecoder.decode(m.getOwner().getAvatar(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                Picasso.with(getContext()).
                        load(avatar).
                        resize(AVATAR_SIZE, AVATAR_SIZE).
                        centerCrop().
                        transform(new CircleTransform()).
                        into(target);

                ++cycle;
            }
        }

        targetsList.clear();
    }

    public class PicassoMarker implements Target {

        Marker mMarker;
        int loadIndicator = 0;

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
            loadIndicator = 1;
        }

        public void onBitmapFailed(Drawable errorDrawable) {
            loadIndicator = -1;
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            int a = 0;
        }

    }

}