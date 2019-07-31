package com.wevois.checkpushnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestQue = Volley.newRequestQueue(this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
    }

    private void sendNotification() {
        JSONObject json = new JSONObject();
        try {

//            json.put("to","/topics/"+"news");
            json.put("to","dvFTrKBobPc:APA91bHCxsmOyJZHJ53J0na1" +
                    "_KU1ZyCmDzkPwNZqugMwgU37XwJcN0P2017MLQiBUh14M959h_zWQnIuu_E" +
                    "jejrpy66k3mVDzSIhLe_2qnCvnHXGMJ_vF3rcGlPgS4LHFs1OoExu-1i_");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title","any title");
            notificationObj.put("body","any body");
            json.put("notification",notificationObj);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("MUR", "onResponse: "+response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("MUR", "onError: "+error.networkResponse);
                }
            }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-Type","application/json");
//                    header.put("authorization","key=AIzaSyCXX0s1d82RMrPaSENLkwaEo9Fj9VvSt4A");
                    header.put("authorization","key=AAAAWLxrVRI:APA91bGFMeeQoUzVDlX2a7IBYOK76e9D2D3uB6kg" +
                            "UrfMcuZDhw0g9ciEUSSljMH3em_Yfh2Wie4Dp54z1T0zABESSMbFs52enD2ZM1q1ZOLQotBcdnK7-X9j6WUY" +
                            "zfwC8mlCvYsyZkt7");
                    return header;
                }
            };
            mRequestQue.add(request);
        }
        catch (JSONException e)

        {
            e.printStackTrace();
        }
    }

}
