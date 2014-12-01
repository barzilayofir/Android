package com.projects.ofirbarzilay.animalsounds;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Ofir.Barzilay on 30/11/2014.
 */
public class FullScreenImageAdapter extends PagerAdapter {

    private Activity _activity;
    private LayoutInflater inflater;
    // Keep all Sounds in array
    public Integer[] mThumbSounds = {
            R.raw.sound_1, R.raw.sound_2,
            R.raw.sound_3, R.raw.sound_4,
            R.raw.sound_5, R.raw.sound_6,
            R.raw.sound_7, R.raw.sound_8,
            R.raw.sound_9, R.raw.sound_10,
            R.raw.sound_11, R.raw.sound_12,
            R.raw.sound_13, R.raw.sound_14,
            R.raw.sound_15
    };

    // Keep all Sounds in array
    public Integer[] mThumbNameSounds = {
            R.raw.name_1, R.raw.name_2,
            R.raw.name_3, R.raw.name_4,
            R.raw.name_5, R.raw.name_6,
            R.raw.name_7, R.raw.name_8,
            R.raw.name_9, R.raw.name_10,
            R.raw.name_11, R.raw.name_12,
            R.raw.name_13, R.raw.name_14,
            R.raw.name_15
    };

    // constructor
    public FullScreenImageAdapter(Activity activity) {
        this._activity = activity;
    }

    @Override
    public int getCount() {
        return mThumbNameSounds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imgDisplay;
        Button btnClose;

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.full_image, container,
                false);

        AnimalImageAdapter imageAdapter = new AnimalImageAdapter(_activity);

        ImageView imageView = (ImageView) viewLayout.findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
        imageView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                MediaPlayerHandler.getInstance().startNameSound(_activity.getApplicationContext(), mThumbSounds[position]);
            }
        });


        Button nameButton = (Button)viewLayout.findViewById(R.id.animal_name_button);
        nameButton.setText(_activity.getString(imageAdapter.mThumbNames[position]));

        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound_1);
        nameButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                MediaPlayerHandler.getInstance().startNameSound(_activity.getApplicationContext(), mThumbNameSounds[position]);
            }
        });

        /*imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
        btnClose = (Button) viewLayout.findViewById(R.id.btnClose);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
        imgDisplay.setImageBitmap(bitmap);

        // close button click event
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _activity.finish();
            }
        });*/

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);

    }
}
