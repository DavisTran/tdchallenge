package com.helloworld.davistran.tds;

import java.util.ArrayList;

/**
 * Created by Davis on 11/20/2016.
 */

public class Info {
    private String iName;
    private String iDescription;
    private int iIndex;

    public Info(String name, String description, int index)
    {
        iName = name;
        iDescription = description;
        iIndex = index;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getiDescription() {
        return iDescription;
    }

    public void setiDescription(String iDescription) {
        this.iDescription = iDescription;
    }

    public int getiIndex() {
        return iIndex;
    }

    public void setiIndex(int iIndex) {
        this.iIndex = iIndex;
    }

    private static int lastInfoId = 0;

    public static ArrayList<Info> createInfoList(int numInfo)
    {
        ArrayList<Info> infos = new ArrayList<Info>();
        for(int i = 1; i <= numInfo; i++)
        {
            infos.add(new Info("Person " + ++lastInfoId, "I am number " + i, i));
        }

        return infos;
    }
}
