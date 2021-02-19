package com.example.wallpaprefirebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements RecyclerViewAdapter.ImageClickListener {
    private String url;
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("wallpaper");
        loadData();
        }
    private void loadData() {
        final ArrayList<String> urls = new ArrayList<>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    urls.add(dataSnapshot1.getValue().toString());
                }
                recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this, urls));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Erorr", databaseError.toString());
            }
        });
    }

    @Override
    public void imageClicked(String url) {
        this.url = url;
        Intent intent = new Intent(this, Detailsimage.class);
        intent.putExtra("image_url", url);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
case  R.id.Share:
    Intent sharingIntent = new Intent();
    sharingIntent.setType("text/plain");
   sharingIntent.setAction(Intent.ACTION_SEND);
   sharingIntent.putExtra(Intent.EXTRA_TEXT,"Send");
   Intent shareintent=Intent.createChooser(sharingIntent,null);
   startActivity(shareintent);

            break;
            case R.id.Contactus:
                String[] TO = {"eslam.ee600@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Offline Wallpaper");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Type the message you would like to send to the devs");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Sending Email to..."));
                    //finish();
                    //Log.i("Finished sending email", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.Exit:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}