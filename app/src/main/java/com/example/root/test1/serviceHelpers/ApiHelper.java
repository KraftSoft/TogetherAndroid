package com.example.root.test1.serviceHelpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.root.test1.R;
import com.example.root.test1.service.ApiService;

/**
 * Created by kic on 1/6/17.
 */

public class ApiHelper {

    public final static String ACTION_MEETINGS_LIST = "meetings-list";

    private String API_HOST_URL;

    private Context context;
    private SharedPreferences sharedPreferences;

    public ApiHelper(Context applicationContext) {

        context = applicationContext;
        API_HOST_URL = context.getString(R.string.SERVER_HOST);

        // TODO safekeeping
    }


    public void getMeetingsList(NetworkResultReceiver receiver) {
        // TODO safekeeping

        String url = API_HOST_URL + "/meetings-list/";

        Intent intent = new Intent(context, ApiService.class);

        intent.putExtra("url", url);
        intent.putExtra("type", "list");
        intent.putExtra("action", ACTION_MEETINGS_LIST);
        intent.putExtra("interestedObjectFromJSONResponse",
                new String[]{"field1", "field2"});

        intent.putExtra("receiver", receiver);

        context.startService(intent);
    }
}
