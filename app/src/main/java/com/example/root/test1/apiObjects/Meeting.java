package com.example.root.test1.apiObjects;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class Meeting implements Serializable {

    private Integer ID;
    private String title;
    private String description;

    public User getOwner() {
        return owner;
    }

    private User owner;

    public LatLng getCoordinates() {
        return coordinates;
    }

    private LatLng coordinates;
    private Date dateCreate;


    public Meeting(JSONObject meeting) throws JSONException {

        ID = meeting.getInt("id");
        title = meeting.getString("title");
        description = meeting.getString("description");

        JSONObject rawOwner = meeting.getJSONObject("owner");
        owner = new User(rawOwner);

        JSONObject rawCoordinates;
        rawCoordinates = meeting.getJSONObject("coordinates");

        double coordsLat = rawCoordinates.getDouble("lat");
        double coordsLng = rawCoordinates.getDouble("lng");

        coordinates = new LatLng(coordsLat, coordsLng);

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
