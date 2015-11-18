package com.doni.photoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    String TAG;
    public static final int REQUEST_CODE = 1;
    File file;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = this.getClass().getName();
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void btnClick(View view) {
        if (view.getId() == R.id.bt_take) {
            callCameraApp();
        } else if (view.getId() == R.id.bt_album) {
            Intent intent = new Intent(this, AlbumActivity.class);
            startActivity(intent);
        }
    }

    public void callCameraApp() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +"/MyMedia/photo";
        long currentTime = System.currentTimeMillis();
        String fileName = currentTime + ".jpg";
        file = new File(path, fileName);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, REQUEST_CODE);
    }

    // startActivityForResult 메서드 호출 후 해당 액티비티나 앱이 결과를 보낼 때 자동으로 호출
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Log.d(TAG, "사진앱이 제대로 동작하고 결과보내옴");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 16;

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            imageView.setImageBitmap(bitmap);
        }
    }
}
