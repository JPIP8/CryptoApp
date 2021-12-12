package com.example.idrovo_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsActivity extends AppCompatActivity {

    public static final String API_KEY = "08c7ed86d55e45018b4daf589dddd161";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = NewsActivity.class.getSimpleName();

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    public void LoadJson(){
        apiNewsInterface apiInterface = apiNews.getapiNews().create(apiNewsInterface.class);
        //String country = "us";
        String country = utils.getCountry();

        Call<NewsMod> call;
        call = apiInterface.getNewsMod(country,API_KEY);
        call.enqueue(new Callback<NewsMod>() {
            @Override
            public void onResponse(Call<NewsMod> call, Response<NewsMod> response) {
                if(response.isSuccessful() && response.body().getArticle() != null){
                    if(articles.isEmpty()){
                        articles.clear();}

                    articles = response.body().getArticle();
                    adapter = new Adapter(articles, NewsActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(NewsActivity.this, "No Result wey", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NewsMod> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        recyclerView = findViewById(R.id.recyclerViewNews);
        layoutManager = new LinearLayoutManager(NewsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        LoadJson();


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
                        Toast.makeText(NewsActivity.this, "Home",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        break;
                    case R.id.markets:
                        Toast.makeText(NewsActivity.this, "Markets",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MarketsActivity.class));
                        break;
                    case R.id.news:
                        Toast.makeText(NewsActivity.this, "News",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                        break;
                    case R.id.favs:
                        Toast.makeText(NewsActivity.this, "Favs",Toast.LENGTH_LONG).show();
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


}