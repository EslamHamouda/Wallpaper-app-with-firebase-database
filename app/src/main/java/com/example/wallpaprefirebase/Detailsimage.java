package com.example.wallpaprefirebase;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Detailsimage extends AppCompatActivity {
    private String url;
    ImageView imageView;
    FloatingActionButton floatingActionButton1;
    FloatingActionButton floatingActionButton2;
    FloatingActionButton floatingActionButton3;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsimage);
        imageView=findViewById(R.id.imageView0);
        url = getIntent().getStringExtra("image_url");
        Picasso.get().load(url).fit().centerCrop().into(imageView);

        floatingActionButton1=findViewById(R.id.setBackground);
        floatingActionButton1.setOnClickListener(v -> {
            Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
            WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
            try {
                wallpaperManager.setBitmap(bitmap,null,true,WallpaperManager.FLAG_SYSTEM);
                Toast.makeText(getApplicationContext(),"Set as Wallpaper successfully",Toast.LENGTH_LONG).show();
            }catch (IOException e){
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
        floatingActionButton2=findViewById(R.id.setHome);
        floatingActionButton2.setOnClickListener(v -> {
            Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
            WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
            try {
                wallpaperManager.setBitmap(bitmap);
                Toast.makeText(getApplicationContext(),"Set as Wallpaper successfully",Toast.LENGTH_LONG).show();
            }catch (IOException e){
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });

floatingActionButton3=findViewById(R.id.setLock);
floatingActionButton3.setOnClickListener(v -> {
    Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
    WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
    try {
            wallpaperManager.setBitmap(bitmap,null,true, WallpaperManager.FLAG_LOCK);
        Toast.makeText(getApplicationContext(),"Set as Wallpaper successfully",Toast.LENGTH_LONG).show();
    }catch (IOException e){
        Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
    }
});
    }
}