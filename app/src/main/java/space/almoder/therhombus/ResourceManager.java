package space.almoder.therhombus;

import android.content.Context;

public class ResourceManager {
    Context c;
    int lid;

    ResourceManager(Context c, int lid) {
        this.c = c;
        this.lid = lid;
    }

    public int getIntRes(String name) {
        return c.getResources().getInteger(c.getResources().getIdentifier("f" + lid + name, "integer", c.getPackageName()));
    }

    public int getDimRes(String name) {
        return (int)c.getResources().getDimension(c.getResources().getIdentifier("f" + lid + name, "dimen", c.getPackageName()));
    }

    public int[] getArrRes(String name) {
        return c.getResources().getIntArray(c.getResources().getIdentifier("f" + lid + name, "array", c.getPackageName()));
    }

    public int[][] getFieldRes() {
        int[][] ret = new int[getIntRes("rows")][];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = getArrRes("_" + (i + 1));
        }
        return ret;
    }

    public int[] getFieldColors() {
        if (getIntRes("colors") == 0) {
            return c.getResources().getIntArray(c.getResources().getIdentifier("f0colors", "array", c.getPackageName()));
        }
        else return getArrRes("colors");
    }

    public int getMinWD() {
        return getDimRes("minWD");
    }

    public int getMaxWD() {
        return getDimRes("maxWD");
    }

    public int getP1Color() {
        return c.getResources().getColor(c.getResources().getIdentifier("p1Color", "color", c.getPackageName()));

    }

    public int getP2Color() {
        return c.getResources().getColor(c.getResources().getIdentifier("p2Color", "color", c.getPackageName()));

    }

    public Context getContext() {
        return c;
    }
}
