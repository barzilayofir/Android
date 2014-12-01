package com.projects.ofirbarzilay.animalsounds;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Ofir.Barzilay on 28/11/2014.
 */
public class AnimalImageAdapter extends BaseAdapter {
    private Context mContext;


    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.pic_1, R.drawable.pic_2,
            R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_8,
            R.drawable.pic_9, R.drawable.pic_10,
            R.drawable.pic_11, R.drawable.pic_12,
            R.drawable.pic_13, R.drawable.pic_14,
            R.drawable.pic_15
    };

    public Integer[] mThumbNames = {
            R.string.pic_1, R.string.pic_2,
            R.string.pic_3, R.string.pic_4,
            R.string.pic_5, R.string.pic_6,
            R.string.pic_7, R.string.pic_8,
            R.string.pic_9, R.string.pic_10,
            R.string.pic_11, R.string.pic_12,
            R.string.pic_13, R.string.pic_14,
            R.string.pic_15
    };

    public Animal[] mAnimalsArray = new Animal[]{};

    // Constructor
    public AnimalImageAdapter(Context c){
        mContext = c;
    }


    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //imageView.setLayoutParams(new GridView.LayoutParams(70, 70));

        //Calculation of ImageView Size - density independent.
        //maybe you should do this calculation not exactly in this method but put is somewhere else.
        Resources r = Resources.getSystem();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, r.getDisplayMetrics());
        imageView.setLayoutParams(new GridView.LayoutParams((int)px, (int)px));

        return imageView;
    }

}
