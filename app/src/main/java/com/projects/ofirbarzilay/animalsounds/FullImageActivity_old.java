package com.projects.ofirbarzilay.animalsounds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class FullImageActivity_old extends Activity {

    private int mPosition;

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
    private ViewPager viewPager;
    private FullScreenImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.full_image);

        setContentView(R.layout.activity_fullscreen_view);

        viewPager = (ViewPager) findViewById(R.id.pager);

        adapter = new FullScreenImageAdapter(FullImageActivity_old.this);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        //viewPager.setCurrentItem(position);


        // get intent data
        Intent intent = getIntent();

        // Selected image id
        Bundle extras = intent.getExtras();

        mPosition = extras.getInt("id");
        AnimalImageAdapter imageAdapter = new AnimalImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds[mPosition]);

        Button nameButton = (Button)findViewById(R.id.animal_name_button);
        nameButton.setText(getString(imageAdapter.mThumbNames[mPosition]));

        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound_1);
        nameButton.setOnClickListener(new OnClickListener(){

            public void onClick(View v) {
                MediaPlayerHandler.getInstance().startNameSound(getApplicationContext(), mThumbNameSounds[mPosition]);
            }
        });
    }


    public void playSound(View view){
        MediaPlayerHandler.getInstance().startAnimalSound(getApplicationContext(), mThumbSounds[mPosition]);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
