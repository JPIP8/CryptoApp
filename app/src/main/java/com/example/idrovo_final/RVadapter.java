package com.example.idrovo_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVadapter extends RecyclerView.Adapter<RVadapter.MyViewHolder> {

    // Initializing variables
    String data1[], data2[];
    int images[];
    Context context;

    // Adding the constructor for my recycler view adapter class
    // We must pass the values from MarketActivity to RVadapter class
    public RVadapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    // Created 3 different methods for the functionality of teh code
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rows, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt1.setText(data1[position]);
        holder.txt2.setText(data2[position]);
        holder.img1.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        //return 10;
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        // Create text objects for title and description
        TextView txt1, txt2;
        ImageView img1;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.title_txt);
            txt2 = itemView.findViewById(R.id.info_txt);
            img1 = itemView.findViewById(R.id.logo_img);

        }
    }
}
