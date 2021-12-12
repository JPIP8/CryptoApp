package com.example.idrovo_final;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    TextView coinName;
    TextView coinID;
    TextView coinPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.markets);
        MarketData m = MarketsActivity.getMarketInstance();
        coinName = findViewById(R.id.title_txt);
        coinPrice = findViewById(R.id.info_txt);
        //coinID = findViewById(R.id.id_txt);

        coinName.setText(m.getCoinName());
        coinPrice.setText("$ " + m.getCoinPrice());
        //coinID.setText(m.getCoinID());

    }
}
