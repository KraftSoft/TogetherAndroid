package com.example.root.test1.responseObjects;

import android.content.Intent;

import org.json.JSONException;

/**
 * Created by kic on 1/7/17.
 */
public interface ResponseAction {
    void handle(Intent intent) throws JSONException;
}
