package com.doni.photoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {
    String TAG;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TAG = this.getClass().getName();
        imageView = (ImageView)findViewById(R.id.imageView);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        Bitmap bitmap = bundle.getParcelable("img");
        Log.d(TAG, "bitmap : " + bitmap);
        imageView.setImageBitmap(bitmap);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
}
