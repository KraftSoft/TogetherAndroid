package com.example.root.test1.responseActions;

import android.content.Intent;
import android.util.Log;

import com.example.root.test1.utils.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by kic on 1/7/17.
 */

abstract class BaseAction {

    protected abstract void handleItem(JSONObject jsonObject) throws JSONException;

    protected void handleObject(String urlString, Intent intent) {

        JSONObject response_json = null;
        JsonParser parserJson = new JsonParser();

        try {

            response_json = parserJson.getObject(urlString);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.e(JsonParser.log_json, "Error");
        }

        if (response_json != null) {
            JSONArray arr = null;

            String[] interestedObjects = intent.getStringArrayExtra("interestedObjectFromJSONResponse");

        }

    }

    protected void handleList(String urlString, Intent intent) throws JSONException {

        JSONArray responseJson = null;
        JsonParser parserJson = new JsonParser();

        try {

            responseJson = parserJson.getList(urlString);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.e(JsonParser.log_json, "Error");
        }

        if (responseJson != null) {

            String[] interestedObjects = intent.getStringArrayExtra("interestedObjectFromJSONResponse");

            for (int i = 0; i < responseJson.length(); i++) {
                JSONObject listItem = responseJson.getJSONObject(i);
                handleItem(listItem);

            }

        }

    }
}
