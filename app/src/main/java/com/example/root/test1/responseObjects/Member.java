package com.example.root.test1.responseObjects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kic on 1/7/17.
 */
public class Member {

    private Integer ID;
    private String userName;
    private String about;
    private String firstName;

    public Member(JSONObject member) throws JSONException {

        ID = member.getInt("id");
        userName = member.getString("username");
        about = member.getString("about");
        firstName = member.getString("firstName");

    }
}
