package com.example.json1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView T;
    private Button B;
    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        T = (TextView) findViewById(R.id.t1);
        B = (Button) findViewById(R.id.b1);
        rq = Volley.newRequestQueue(this);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonparse();
            }
        });
    }

    private void jsonparse() {
        String url = "https://api.mfapi.in/mf/120248";
        JsonObjectRequest r = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray a = response.getJSONArray("data");
                    for (int i = 0; i < 40; i++) {
                        JSONObject d=a.getJSONObject(i);
                        String date=d.getString("date");
                        Double nav=d.getDouble("nav");
                        T.append(date+" "+"  a"+String.valueOf(nav)+"\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        rq.add(r);
    }
    //push
}