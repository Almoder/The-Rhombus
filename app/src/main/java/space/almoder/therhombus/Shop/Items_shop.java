package space.almoder.therhombus.Shop;

import android.graphics.drawable.Drawable;

public class Items_shop {
    private Drawable icProduct;
    private String name;
    private String cost;


    public Items_shop(String name, String cost, Drawable Res){

        this.name=name;
        this.cost = cost;
        this.icProduct =Res;
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
}
