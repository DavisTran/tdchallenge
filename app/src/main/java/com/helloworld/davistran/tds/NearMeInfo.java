package com.helloworld.davistran.tds;

import java.util.ArrayList;

/**
 * Created by Davis on 11/22/2016.
 */

public class NearMeInfo {

    private String nName;
    private String nDistance;
    private String nBusy;
    private ArrayList<String> img;
    private boolean selected;

    public NearMeInfo(String name, String dist, String busy, ArrayList<String> img)
    {
        nName = name;
        nDistance = dist;
        nBusy = busy;
        this.img = img;
        selected = false;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean select)
    {
        this.selected = select;
    }

    public String getnDistance() {
        return nDistance;
    }

    public void setnDistance(String nDistance) {
        this.nDistance = nDistance;
    }

    public String getnBusy() {
        return nBusy;
    }

    public void setnBusy(String nBusy) {
        this.nBusy = nBusy;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public ArrayList<String> getImg() {
        return img;
    }

    public void setImg(ArrayList<String> img) {
        this.img = img;
    }
}
