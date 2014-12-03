package com.projects.ofirbarzilay.animalsounds.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.ofirbarzilay.animalsounds.MainActivity;
import com.projects.ofirbarzilay.animalsounds.MediaPlayerHandler;
import com.projects.ofirbarzilay.animalsounds.R;
import com.projects.ofirbarzilay.animalsounds.helper.AppConstant;

/**
 * Created by Ofir.Barzilay on 30/11/2014.
 */
public class FullScreenImageAdapter extends PagerAdapter {

    private Activity _activity;
    private LayoutInflater inflater;


    // constructor
    public FullScreenImageAdapter(Activity activity) {
        this._activity = activity;
    }

    @Override
    public int getCount() {
        return AppConstant.ThumbNameSounds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.full_image, container,
                false);



        ImageView imageView = (ImageView) viewLayout.findViewById(R.id.full_image_view);
        imageView.setImageResource(AppConstant.ThumbIds[position]);
        imageView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                MediaPlayerHandler.getInstance().startNameSound(_activity.getApplicationContext(), AppConstant.ThumbSounds[position]);
            }
        });


        TextView nameButton = (TextView)viewLayout.findViewById(R.id.animal_name_text);
        nameButton.setText(_activity.getString(AppConstant.ThumbNames[position]));

        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound_1);
        nameButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                MediaPlayerHandler.getInstance().startNameSound(_activity.getApplicationContext(), AppConstant.ThumbNameSounds[position]);
            }
        });

        ImageView nextButton = (ImageView)viewLayout.findViewById(R.id.swipe_next_button);
        nextButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) _activity.findViewById(R.id.pager);
                viewPager.setCurrentItem(getNextPosition(position), true);
            }
        });

        ImageView previousButton = (ImageView)viewLayout.findViewById(R.id.swipe_previous_button);
        previousButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                ViewPager viewPager = (ViewPager) _activity.findViewById(R.id.pager);
                viewPager.setCurrentItem(getPreviousPosition(position), true);
            }
        });

        ImageView homeButton = (ImageView)viewLayout.findViewById(R.id.swipe_home_button);
        homeButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(_activity.getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                _activity.startActivity(intent);
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

    private int getNextPosition(int position){
        int newPosition;
        int size = AppConstant.ThumbIds.length;
        if (position + 1 < size){
            newPosition = position + 1;
        }
        else{
            newPosition = 0;
        }
        return newPosition;
    }

    private int getPreviousPosition(int position){
        int newPosition;
        int size = AppConstant.ThumbIds.length;
        if (position - 1 >= 0){
            newPosition = position - 1;
        }
        else{
            newPosition = size -1;
        }
        return newPosition;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);

    }
}
