package com.example.idrovo_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        Toast.makeText(MainActivity.this, "Home",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        break;
                    case R.id.markets:
                        Toast.makeText(MainActivity.this, "Markets",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MarketsActivity.class));
                        System.out.println("Inside Markets - MainActivity.java");
                        break;
                    case R.id.news:
                        Toast.makeText(MainActivity.this, "News",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this, NewsActivity.class));
                        break;
                    case R.id.favs:
                        Toast.makeText(MainActivity.this, "Favs",Toast.LENGTH_LONG).show();
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