package com.example.idrovo_final;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class AdapterCrypto extends RecyclerView.Adapter<AdapterCrypto.MyViewHolder> {

    private List<ArticleCrypto> articles;
    private Context context;
    private OnItemClickListener OnItemClickListener;


    public AdapterCrypto(List<ArticleCrypto> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rows, parent, false);
        return new MyViewHolder(view, OnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        ArticleCrypto model = articles.get(position);
        holder.id.setText(model.getId());
        holder.symbol.setText(model.getSymbol());
        holder.name.setText(model.getName());
        holder.current_price.setText(model.getCurrent_price());
    }

    @Override
    public int getItemCount() {
        //return 10;
        return articles.size();
    }

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener){
        this.OnItemClickListener = OnItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView id, symbol, name, current_price;
        ImageView imageView;
        OnItemClickListener onItemClickListener;
        public MyViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            id = itemView.findViewById(R.id.id_txt);
            symbol = itemView.findViewById(R.id.id_txt);
            name = itemView.findViewById(R.id.name_txt);
            current_price = itemView.findViewById(R.id.price_txt);
            imageView = itemView.findViewById(R.id.news_img);

            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
