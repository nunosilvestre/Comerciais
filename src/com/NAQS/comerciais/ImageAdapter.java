package com.NAQS.comerciais;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
 
public class ImageAdapter extends BaseAdapter
    {
        private Context _context = null;
        private final int[] imageIds = { R.drawable.prod01, R.drawable.prod02, R.drawable.forn01, R.drawable.forn02};
 
        public ImageAdapter(Context context)
            {
                this._context = context;
 
            }
 
        @Override
        public int getCount()
            {
                return imageIds.length;
            }
 
        @Override
        public Object getItem(int index)
            {
                return imageIds[index];
            }
 
        @Override
        public long getItemId(int index)
            {
                return index;
            }
 
        @Override
        public View getView(int postion, View view, ViewGroup group)
            {
                ImageView imageView = new ImageView(_context);
 
                imageView.setImageResource(imageIds[postion]);
                imageView.setScaleType(ScaleType.FIT_XY);
                imageView.setLayoutParams(new Gallery.LayoutParams(400, 400));
                return imageView;
            }
    }