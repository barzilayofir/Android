package com.projects.ofirbarzilay.animalsounds;

/**
 * Created by Ofir.Barzilay on 03/12/2014.
 */
public class Animal {
    private int mPictureID;
    private int mSoundID;
    private int mNameSoundID;
    private int mNameID;

    public void Animal(){

    }

    public int getNameID() {
        return mNameID;
    }

    public void setNameID(int nameID) {
        this.mNameID = nameID;
    }

    public int getPictureID() {
        return mPictureID;
    }

    public void setPictureID(int pictureID) {
        this.mPictureID = pictureID;
    }

    public int getSoundID() {
        return mSoundID;
    }

    public void setSoundID(int soundID) {
        this.mSoundID = soundID;
    }

    public int getNameSoundID() {
        return mNameSoundID;
    }

    public void setNameSoundID(int nameSoundID) {
        this.mNameSoundID = nameSoundID;
    }




}
