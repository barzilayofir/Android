package com.projects.ofirbarzilay.animalsounds.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.projects.ofirbarzilay.animalsounds.helper.ResourceManager;

/**
 * Created by Ofir.Barzilay on 28/11/2014.
 */
public class AnimalImageAdapter extends BaseAdapter {
    private final int mImageWidth;
    private Context mContext;

    // Constructor
    public AnimalImageAdapter(Context c, int columnWidth) {
        mContext = c;
        mImageWidth = columnWidth;
    }


    @Override
    public int getCount() {
        return ResourceManager.getNumOfAnimals();
    }

    @Override
    public Object getItem(int position) {
        return ResourceManager.getAnimal(position).getPictureID();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(ResourceManager.getAnimal(position).getPictureID());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(mImageWidth,
                mImageWidth));
        //imageView.setLayoutParams(new GridView.LayoutParams(70, 70));

        //Calculation of ImageView Size - density independent.
        //maybe you should do this calculation not exactly in this method but put is somewhere else.
        //Resources r = Resources.getSystem();
        //float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, r.getDisplayMetrics());
        //imageView.setLayoutParams(new GridView.LayoutParams((int)px, (int)px));

        return imageView;
    }


}
