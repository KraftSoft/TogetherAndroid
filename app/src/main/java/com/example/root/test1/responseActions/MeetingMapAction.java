package com.example.root.test1.responseActions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

import com.example.root.test1.apiObjects.Meeting;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kic on 1/7/17.
 */
public class MeetingMapAction extends BaseAction implements ResponseAction {

    protected ArrayList<Meeting> meetingList = new ArrayList<>();

    @Override
    public void handle(Intent intent) throws JSONException {

        String urlString = intent.getStringExtra("url");
        final String responseType = intent.getStringExtra("type");
        int code;

        if (responseType.equals("list")) {
            handleList(urlString, intent);
            code = 0;
        }
        else {
            Log.e("error", "wrong response type for meetings list");
            code = 1;
        }

        ResultReceiver receiver = intent.getParcelableExtra("receiver");

        Bundle bundle = new Bundle();
        bundle.putSerializable("meetingsList", meetingList);

        receiver.send(code, bundle);
    }

    @Override
    protected void handleItem(JSONObject jsonObject) throws JSONException {
        meetingList.add(new Meeting(jsonObject));
    }
}
