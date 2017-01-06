package com.example.root.test1.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.root.test1.R;
import com.example.root.test1.utils.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by kic on 1/6/17.
 */

public class ApiService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public ApiService(String name) {
        super(name);
    }

    public ApiService() {
        super("ApiService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("service", "RestService starting...");

        String urlString = intent.getStringExtra("url");
        JSONObject response_json = null;

        try {
            response_json = JsonParser.getJsonFromUrl(urlString);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.e(JsonParser.log_json, "Error");
        }

        if (response_json != null)
            try {
                JSONArray arr = null;

                String[] interestedObjects = intent.getStringArrayExtra("interestedObjectFromJSONResponse");
                if (intent.getStringExtra("no_items") == null) {
                    JSONObject response = response_json.getJSONObject("response");
                    arr = response.getJSONArray("items");
                } else {
                    arr = response_json.getJSONArray("response");
                }

                String table_name = intent.getStringExtra("table_name");

                for (int i = 0; i < arr.length(); i++) {
                    int a = 0;
                }

            } catch (JSONException e) {
                Log.e(JsonParser.log_json, "Can not parse json");
            }
    }
}
