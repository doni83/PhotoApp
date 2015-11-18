package com.doni.photoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class AlbumActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gridView;
    AlbumAdapter albumAdapter;
    String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        TAG = this.getClass().getName();
        gridView = (GridView)findViewById(R.id.gridView);
        albumAdapter = new AlbumAdapter(this);
        gridView.setAdapter(albumAdapter);
        gridView.setOnItemClickListener(this);
    }

    public void btnClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, position + " 나 눌렀음?");
        ImageView imageView = (ImageView)view;
        imageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = imageView.getDrawingCache();
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("img", bitmap);
        intent.putExtra("bundle", bundle);

        startActivity(intent);
    }
}
