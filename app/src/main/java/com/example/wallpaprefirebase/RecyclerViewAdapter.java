package com.example.wallpaprefirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MainViewHolder> {

    private Context context;
    private ArrayList<String> urls;
    private ImageClickListener listener;

    public RecyclerViewAdapter(Context context, ArrayList<String> urls) {
        this.context = context;
        this.urls = urls;
        listener = (ImageClickListener) context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.form, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.setData(urls.get(position));
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            listener.imageClicked(urls.get(getAdapterPosition()));

        }

        public void setData(String url) {
            Picasso.get().load(url).fit().centerInside().into(imageView);
        }
    }

    public interface ImageClickListener{
        void imageClicked(String url);
    }

}
