package com.projects.ofirbarzilay.funtime.animalsounds.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.projects.ofirbarzilay.funtime.animalsounds.Animal;
import com.projects.ofirbarzilay.funtime.animalsounds.helper.ResourceManager;


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
        ImageView imageView;
        if (convertView == null) {//TODO improve performance here, perhaps use thumbnails

            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(mImageWidth,
                    mImageWidth));
        }
        else{
            imageView = (ImageView)convertView;
        }
        Animal animal = ResourceManager.getAnimal(position);
        int pictureID = animal.getPictureID();
        //Log.d("picture id", "animal name: " + mContext.getString(animal.getNameID()));
        imageView.setImageResource(pictureID);



        return imageView;
    }


}
