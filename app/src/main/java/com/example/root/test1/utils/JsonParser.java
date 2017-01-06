package com.example.root.test1.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by kic on 1/6/17.
 */

public class JsonParser {
    public static final String log_json = "JsonParserLogs";

    public JSONObject getObject(String url) throws IOException, JSONException {
        JSONObject jsonResult = null;

        String jsonString = getJSONbyURL(url);
        jsonResult = new JSONObject(jsonString);

        return jsonResult;
    }

    public JSONArray getList(String url) throws IOException, JSONException {
        JSONArray jsonResult = null;

        String jsonString = getJSONbyURL(url);
        if (jsonString != null) {
            jsonResult = new JSONArray(jsonString);
        }
        else {
            Log.e(log_json, "can not get response"); // TODO TOAST
        }
        return jsonResult;
    }

    private String reader_to_string(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
//        while ((inputLine = in.readLine()) != null) {
//            sb.append(inputLine);
//        }

        return sb.toString();
    }

    private String getJSONbyURL(String url) throws IOException {

        BufferedReader rd = null;
        String resultJson = null;

        try {

            URL urlObject = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection)urlObject.openConnection();
            urlConnection.setRequestProperty("Authorization", "Token 163df7faa712e242f7e6b4d270e29401e604b9b2");

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8")));

                resultJson = reader_to_string(rd);
            }
            else if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
                Log.e(log_json, "Authorization token problems");
            }
            else {
                Log.e(log_json, "Bad response");
            }
        } catch (IOException e) {
            Log.e(log_json, "Could not connect to server");
        } finally {
            if (rd != null)
                rd.close();
        }
        return resultJson;
    }

}