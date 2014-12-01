package com.projects.ofirbarzilay.animalsounds;

/**
 * Created by Ofir.Barzilay on 28/11/2014.
 */
public class Animal {
    private String name;
    private String fileName;
    private int id;

    public Animal(String name, int id) {
        this.name = name;
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
