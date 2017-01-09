package com.example.root.test1.utils;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.root.test1.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by kic on 1/10/17.
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View myContentsView;

    public CustomInfoWindowAdapter(Activity activity){
        myContentsView = activity.getLayoutInflater().inflate(R.layout.info_window, null);
    }

    @Override
    public View getInfoContents(Marker marker) {

        TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
        tvTitle.setText(marker.getTitle());
        TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
        tvSnippet.setText(marker.getSnippet());

        return myContentsView;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        return null;
    }

}
