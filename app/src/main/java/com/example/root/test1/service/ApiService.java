package com.example.root.test1.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.root.test1.responseActions.MeetingMapAction;
import com.example.root.test1.responseActions.ResponseAction;
import com.example.root.test1.serviceHelpers.ApiHelper;

import org.json.JSONException;

import java.util.HashMap;

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
