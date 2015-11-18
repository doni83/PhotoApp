package com.doni.photoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by student on 2015-11-17.
 */
public class AlbumAdapter extends BaseAdapter {
    Context context;
    ArrayList<ImageView> list = new ArrayList<ImageView>();

    public AlbumAdapter(Context context) {
        this.context = context;
        init();
    }

    public void init() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyMedia/photo";

        File file = new File(path);
        File listFile[] = file.listFiles();

        for (int i = 0; i < listFile.length; i++) {
            if (listFile[i].isFile()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 16;
                Bitmap bitmap = BitmapFactory.decodeFile(listFile[i].getAbsolutePath(), options);

                // 그래픽의 왜곡처리를 담당하는 객체인 Matrix를 사용
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                ImageView imageView = new ImageView(context);
                imageView.setImageBitmap(rotateBitmap);
                list.add(imageView);
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        view = list.get(position);

        return view;
    }
}
