package com.example.root.test1.responseObjects;

import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kic on 1/6/17.
 */


/*
*   title = models.CharField(max_length=32)
    description = models.TextField(blank=True)
    owner = models.ForeignKey(User, related_name='created_meetings', blank=True)
    members = models.ManyToManyField(User, related_name='favorite_meetings', blank=True)
    subway = models.ForeignKey(Subway, null=True, blank=True, related_name='meetings')

    coordinates = gis_models.PointField(null=True, blank=False)

    date_create = models.DateTimeField(auto_now=True)
    last_modify = models.DateTimeField(auto_now_add=True)
*
*
*
*
* */

public class Meeting {

    private Integer ID;
    private String title;
    private String description;
    private ArrayList<Member> members = new ArrayList<Member>();
    private JSONObject owner;
    private LatLng coordinates;
    private Date dateCreate;


    public Meeting(JSONObject meeting) throws JSONException {

        ID = meeting.getInt("id");
        title = meeting.getString("title");
        description = meeting.getString("description");

        JSONArray rawMembers = meeting.getJSONArray("members");

        for(int i = 0; i < rawMembers.length(); ++i) {
            members.add(new Member((JSONObject) rawMembers.get(i)));
        }

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
