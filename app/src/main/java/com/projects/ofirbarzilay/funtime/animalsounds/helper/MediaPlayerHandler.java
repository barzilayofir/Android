package com.projects.ofirbarzilay.funtime.animalsounds.helper;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Ofir.Barzilay on 29/11/2014.
 */
public class MediaPlayerHandler {
    private static  MediaPlayerHandler mMediaPlayerHandler;
    private MediaPlayer mMediaPlayerNameSound;
    private MediaPlayer mMediaPlayerAnimalSound;

    private MediaPlayerHandler(){

    }

    public static MediaPlayerHandler getInstance(){
        if (mMediaPlayerHandler == null) {
            synchronized (MediaPlayerHandler.class) {
                if (mMediaPlayerHandler == null) {
                    mMediaPlayerHandler = new MediaPlayerHandler();
                }
            }
        }
        return mMediaPlayerHandler;
    }

    public void startNameSound(Context ctx, Integer resource ){
        try {
            if (mMediaPlayerNameSound != null) {
                mMediaPlayerNameSound.stop();
            }
            mMediaPlayerNameSound = MediaPlayer.create(ctx, resource);
            mMediaPlayerNameSound.start();
        }
        catch(Exception e){
            //TODO log it
        }
    }

    public void startAnimalSound(Context ctx, Integer resource ){
        try {
            if (mMediaPlayerAnimalSound != null) {
                mMediaPlayerAnimalSound.stop();
            }
            mMediaPlayerAnimalSound = MediaPlayer.create(ctx, resource);
            mMediaPlayerAnimalSound.start();
        }
        catch(Exception e){
            //TODO log it
        }
    }

}
