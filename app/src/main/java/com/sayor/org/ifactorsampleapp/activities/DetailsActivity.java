package com.sayor.org.ifactorsampleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sayor.org.ifactorsampleapp.R;
import com.sayor.org.ifactorsampleapp.adapters.PostAdapter;
import com.sayor.org.ifactorsampleapp.models.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {

    private PostAdapter aPostsList;
    private ListView lvPosts;
    private ArrayList<Post> mPosts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mPosts = new ArrayList<Post>();
        lvPosts = (ListView)findViewById(R.id.lvPosts);
        aPostsList = new PostAdapter(this,mPosts);
        lvPosts.setAdapter(aPostsList);

        // getting the userID using using extras in Intents
        Intent i =getIntent();
        String userID =i.getExtras().getString("userID");

        // doing a GET request on the following REST URL. Volley is a great library which also provides
        // access to POST, PATCH, DELETE request. Given more time, I would have implemented it.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://http://jsonplaceholder.typicode.com/posts?userId="+userID;
        JsonArrayRequest jreq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jo = response.getJSONObject(i);
                                String postID = jo.getString("id");
                                String title = jo.getString("title");
                                String body = jo.getString("body");
                                Post post =new Post();
                                post.setPostID(postID);
                                post.setTitle(title);
                                post.setBody(body);
                                mPosts.add(post);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // to update the list view
                        aPostsList.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        //add request to queue
        queue.add(jreq);
    }


    // call this onClick method with user entered data to create new post for current user
    public void onPost(View v){
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://jsonplaceholder.typicode.com/posts";
        Map<String, String> jsonParams = new HashMap<String, String>();

        // dummy data
        jsonParams.put("id", "5");
        jsonParams.put("title", "tile");
        jsonParams.put("body", "body");

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, URL,

                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };
        queue.add(postRequest);
    }
}
