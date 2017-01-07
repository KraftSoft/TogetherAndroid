package com.example.root.test1.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.root.test1.R;
import com.example.root.test1.responseObjects.MeetingMapAction;
import com.example.root.test1.responseObjects.ResponseAction;
import com.example.root.test1.serviceHelpers.ApiHelper;
import com.example.root.test1.utils.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by kic on 1/6/17.
 */

public class ApiService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public HashMap<String, ResponseAction> actionsResponse = new HashMap<String, ResponseAction>();

    public ApiService(String name) {
        super(name);
    }

    public ApiService() {
        super("ApiService");
        actionsResponse.put(ApiHelper.ACTION_MEETINGS_LIST, new MeetingMapAction());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("service", "RestService starting...");

        final String actionName = intent.getStringExtra("action");

        ResponseAction responseAction = actionsResponse.get(actionName);

        try {
            responseAction.handle(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
