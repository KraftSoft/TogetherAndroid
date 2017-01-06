package com.example.root.test1.responseObjects;

import android.content.Intent;
import android.util.Log;

import org.json.JSONException;

/**
 * Created by kic on 1/7/17.
 */
public class MeetingMapAction extends BaseAction implements ResponseAction {

    @Override
    public void handle(Intent intent) throws JSONException {

        String urlString = intent.getStringExtra("url");
        final String responseType = intent.getStringExtra("type");

        if (responseType.equals("list")) {
            handleList(urlString, intent);
        }
        else if (responseType.equals("object")) {
            handleObject(urlString, intent);
        }
        else {
            Log.e("error", "unknown response type");
        }

    }
}
