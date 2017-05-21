package com.sayor.org.ifactorsampleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sayor.org.ifactorsampleapp.R;
import com.sayor.org.ifactorsampleapp.adapters.UserAdapter;
import com.sayor.org.ifactorsampleapp.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private UserAdapter aUsersList;
    private ListView lvUsers;
    private ArrayList<User> mUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsers = new ArrayList<User>();

        // using listview for simple list. would could recyclerview if I had time
        lvUsers = (ListView)findViewById(R.id.lvUsers);
        aUsersList = new UserAdapter(this,mUsers);
        lvUsers.setAdapter(aUsersList);

        // setting listeners for item click
        lvUsers.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                i.putExtra("userID", mUsers.get(position).getUserID());
                startActivity(i);
            }
        });

        // doing a GET request on the following REST URL. Volley is a great library which also provides
        // access to POST, PATCH, DELETE request. Given more time, I would have implemented it.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://jsonplaceholder.typicode.com/users";
        JsonArrayRequest jreq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jo = response.getJSONObject(i);
                                String username = jo.getString("username");
                                String userID = jo.getString("id");
                                Toast.makeText(MainActivity.this,response.toString(), Toast.LENGTH_SHORT ).show();
                                JSONObject jAdd= jo.getJSONArray("address").getJSONObject(0);
                                String address = jAdd.getString("street")+" "+jAdd.getString("suite")
                                                    +" "+jAdd.getString("city")+" "+jAdd.getString("zipcode");
                                User user = new User();
                                user.setUserID(userID);
                                user.setUserName(username);
                                user.setAddress(address);
                                mUsers.add(user);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        // to update the list view
                        aUsersList.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        //add request to queue
        queue.add(jreq);
    }
}
