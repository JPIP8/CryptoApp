package com.example.idrovo_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FavsActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    public static FavsData data = new FavsData();
    //public static MarketData data = new MarketData();


    // Volley request queue
    private RequestQueue queue;

    // Creating recycler view object
    RecyclerView recyclerView;

    // Creating two arrays that will carry the information of the crypto information
    String s1[], s2[];
    int images[] = {R.drawable.eth_img, R.drawable.btc_img, R.drawable.bnb_img, R.drawable.tether_img, R.drawable.solana_img,
            R.drawable.cardano_img, R.drawable.usdc_img, R.drawable.eth_img, R.drawable.eth_img, R.drawable.eth_img};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favs);

        // Finding id for recycler view
        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.coin_name);
        s2 = getResources().getStringArray(R.array.coin_price);

        // Creating Recycler view adapter object
        FavsAdapter Adapter = new FavsAdapter(this, s1, s2, images);

        // Set adapter in our create method
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Instantiate the request queue for the volley
        queue = Volley.newRequestQueue(this);

        getMarketInfo("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false");

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.home:
                        Toast.makeText(FavsActivity.this, "Home",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        break;
                    case R.id.markets:
                        Toast.makeText(FavsActivity.this, "Markets",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MarketsActivity.class));
                        break;
                    case R.id.news:
                        Toast.makeText(FavsActivity.this, "News",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                        break;
                    case R.id.favs:
                        Toast.makeText(FavsActivity.this, "Favs",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), FavsActivity.class));
                        break;

                    default:
                        return true;
                }


                return true;

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    // TODO: DO NOT FORGET ABOUT THIS
    public static FavsData getMarketInstance() {
        return data;
    }

    public void getMarketInfo(String url){
        System.out.println(url);
        queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            //JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONObject response) {
                //public void onResponse(JSONArray response) {
                System.out.println("Inside onResponse");


                //JSONObject resp = null;

                try {

                    //resp = response.getJSONObject("name");
                    //System.out.println(resp);
                    data.setCoinName(response.getString("name"));
                    data.setCoinPrice(response.getString("current_price"));
                    data.setCoinID(response.getString("symbol"));
                    System.out.println("API Response successful");

                    // Current implementation is just  using static number
                    Intent intent = new Intent(FavsActivity.this, DisplayActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    System.out.println("JSON Whaaatt");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);
        //queue.add(getRequest);
    }
}