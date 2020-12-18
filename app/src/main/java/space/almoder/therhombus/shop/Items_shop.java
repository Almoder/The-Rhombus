package space.almoder.therhombus.shop;

import android.graphics.drawable.Drawable;

public class Items_shop{
    private String name;
    private String cost;
    private Drawable icProduct;
    private final String key;


    public Items_shop(String name, String cost, Drawable icProduct, String key) {
        this.name = name;
        this.cost = cost;
        this.icProduct = icProduct;
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Drawable getIcProduct() {
        return this.icProduct;
    }

    public void setIcProduct(Drawable ItemResource) {
        this.icProduct = ItemResource;
    }

    public String getKey() {
        return key;
    }

}
