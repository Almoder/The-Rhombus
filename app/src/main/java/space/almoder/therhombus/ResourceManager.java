package space.almoder.therhombus;

import android.content.Context;

public class ResourceManager {
    Context c;
    int lid, form, width, height;

    ResourceManager(Context c, int lid) {
        this.c = c;
        this.lid = lid;
    }

    ResourceManager(Context c, int form, int width, int height) {
        this.c = c;
        this.lid = 0;
        this.form = form;
        this.width = width;
        this.height = height;
    }

    public int getIntRes(String name) {
        return getIntRes(lid, name);
    }

    public int getIntRes(int lid, String name) {
        return c.getResources().getInteger(c.getResources().getIdentifier("f" + lid + name, "integer", c.getPackageName()));
    }

    public int getDimRes(String name) {
        return getDimRes(lid, name);
    }

    public int getDimRes(int lid, String name) {
        return (int)c.getResources().getDimension(c.getResources().getIdentifier("f" + lid + name, "dimen", c.getPackageName()));
    }

    public int[] getArrRes(String name) {
        return getArrRes(lid, name);
    }

    public int[] getArrRes(int lid, String name) {
        return c.getResources().getIntArray(c.getResources().getIdentifier("f" + lid + name, "array", c.getPackageName()));
    }

    public int[][] getFieldRes() {
        System.out.println("LID: " + lid);
        if (lid != 0) {
            int[][] ret = new int[getIntRes("rows")][];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = getArrRes("_" + (i + 1));
            }
            return ret;
        }
        else {
            //case 1: return generateRectangle();
            System.out.println("FORM: " + form);
            if (form == 2) {
                return generateRhombus();
            }
            return generateRectangle();
        }
    }

    public int[] getFieldColors() {
        if (getIntRes("colors") == 0 && lid != 0) {
            return c.getResources().getIntArray(c.getResources().getIdentifier("f0colors", "array", c.getPackageName()));
        }
        else return getArrRes(0, "colors");
    }

    public int getMinWD() {
        if (lid != 0) return getDimRes("minWD");
        else return getDimRes(15, "minWD");
    }

    public int getMaxWD() {
        if (lid != 0) return getDimRes("maxWD");
        else return getDimRes(15, "maxWD");
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

    private int[][] generateRectangle() {
        int[][] ret = new int[height * 2 + 1][width * 2 + 1];
        for (int i = 1; i <= height; i += 2) {
            for (int j = 1; j <= width; j += 2) {
                ret[i][j] = 7;
                ret[i-1][j-1] = 4;
                ret[i-1][j] = i == 1 ? 5 : 8;
                ret[i][j-1] = j == 1 ? 6 : 9;
            }
        }
        if (height % 2 == 0) {
            for (int i = 0; i < width; i += 2) {
                ret[height][i] = 4;
                ret[height][i+1] = 8;
            }
        }
        if (width % 2 == 0) {
            for (int i = 0; i < height; i += 2) {
                ret[i][width] = 4;
                ret[i+1][width] = 9;
            }
        }
        if (height % 2 == 0 && width % 2 == 0) {
            ret[height][width] = 4;
        }
        for (int i = 0; i <= height; i++) {
            int temp = ret.length-i-1;
            for (int j = 0; j <= width; j++) {
                ret[i][ret[i].length-j-1] = ret[i][j];
                ret[temp][j] = ret[i][j];
                ret[temp][ret[i].length-j-1] = ret[i][j];
            }
        }
        return ret;
    }

    private int[][] generateRhombus() {
        int[][] ret = new int[height * 2 + 1][width * 2 + 1];
        for (int i = 1; i <= height; i += 2) {
            for (int j = width; j >= width - i; j -= 2) {
                ret[i][j] = 7;
                ret[i-1][j-1] = 4;
                ret[i-1][j] = j == width - i + 1 ? 5 : 8;
                ret[i][j-1] = j == width - i + 1 ? 6 : 9;
            }
        }
        for (int i = 1; i < height; i += 2) {
            for (int j = 1; j < width; j += 2) {
                if (ret[i][j] == 7) continue;
                ret[i][j] = 3;
                ret[i-1][j-1] = 0;
                ret[i-1][j] = 1;
                ret[i][j-1] = 2;
            }
        }
        for (int i = 0; i <= height; i++) {
            int temp = ret.length-i-1;
            for (int j = 0; j <= width; j++) {
                ret[i][ret[i].length-j-1] = ret[i][j];
                ret[temp][j] = ret[i][j];
                ret[temp][ret[i].length-j-1] = ret[i][j];
            }
        }
        return ret;
    }
}
