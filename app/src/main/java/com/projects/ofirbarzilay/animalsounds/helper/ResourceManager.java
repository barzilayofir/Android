package com.projects.ofirbarzilay.animalsounds.helper;

import android.app.Activity;

import com.projects.ofirbarzilay.animalsounds.Animal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Ofir.Barzilay on 29/11/2014.
 */
public class ResourceManager {
    private static ResourceManager mResourceManager;
    private static ArrayList<Animal> sAnimalArrayList = new ArrayList<Animal>();


    private final Activity mActivity;

    private ResourceManager(Activity activity){
        mActivity = activity;
        // create arrays of all relevant resources
        String appJson = loadJSONFromAsset();
        try {
            JSONObject obj = new JSONObject(appJson);
            JSONArray animalNamesArray = obj.getJSONArray("animals");
            Animal animal;
            int identifier;
            for (int i = 0; i < animalNamesArray.length(); i++) {
                animal = new Animal();
                String name = animalNamesArray.getString(i);
                identifier = mActivity.getResources().getIdentifier(name, "drawable", "com.projects.ofirbarzilay.animalsounds");
                if (identifier > 0) {
                    animal.setPictureID(identifier);
                }

                identifier = mActivity.getResources().getIdentifier(name, "raw", "com.projects.ofirbarzilay.animalsounds");
                if (identifier > 0) {
                    animal.setSoundID(identifier);
                }

                identifier = mActivity.getResources().getIdentifier("name_" + name, "raw", "com.projects.ofirbarzilay.animalsounds");
                if (identifier > 0) {
                    animal.setNameSoundID(identifier);
                }

                identifier = mActivity.getResources().getIdentifier(name, "string", "com.projects.ofirbarzilay.animalsounds");
                if (identifier > 0) {
                    animal.setNameID(identifier);
                }

                sAnimalArrayList.add(animal);


                //Log.d("Details-->", jo_inside.getString("formule"));
            }
        }
        catch (Exception e){
            //TODO handle it
        }
        //getResources().getIdentifier("fileX", "drawable","com.projects.ofirbarzilay.animalsounds");
        //getResources().getIdentifier("fileX", "raw","com.yourapppackage.www");
    }

    public static ResourceManager getInstance(Activity activity){
        if (mResourceManager == null) {
            synchronized (ResourceManager.class) {
                if (mResourceManager == null) {
                    mResourceManager = new ResourceManager(activity);
                }
            }
        }
        return mResourceManager;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = mActivity.getAssets().open("app.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static Animal getAnimal(int position){
        return sAnimalArrayList.get(position);
    }

    public static int getNumOfAnimals(){
        return sAnimalArrayList.size();
    }

}
