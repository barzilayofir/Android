package com.projects.ofirbarzilay.animalsounds;

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
        if (mMediaPlayerNameSound != null) {
            mMediaPlayerNameSound.stop();
        }
        mMediaPlayerNameSound = MediaPlayer.create(ctx, resource);
        mMediaPlayerNameSound.start();
    }

    public void startAnimalSound(Context ctx, Integer resource ){
        if (mMediaPlayerAnimalSound != null) {
            mMediaPlayerAnimalSound.stop();
        }
        mMediaPlayerAnimalSound = MediaPlayer.create(ctx, resource);
        mMediaPlayerAnimalSound.start();
    }
}
