package space.almoder.therhombus.shop;

import space.almoder.therhombus.R;

public class ProductList {

    private String[] ProductDataKey= new String[]{"ic1", "ic2", "ic3", "ic4", "ic5", "ic6", "ic7",
            "ic8", "ic9", "ic10", "ic11", "ic12"};
    private Integer[] IcDataID= new Integer[]{R.drawable.i1, R.drawable.i2,R.drawable.i3,R.drawable.i4,
            R.drawable.i5, R.drawable.i6,R.drawable.i7,R.drawable.i8,
            R.drawable.i9, R.drawable.i10, R.drawable.i11, R.drawable.i12};
    private String[] ProductName = new String[]{"Heart", "Heartless", "Black star", "The Sun", "Dirty", "Empty"
            , "Cross", "Prohibited", "High five", "Exactly", "Footprint", "Bloody Cross"};
    private int icCoinCost =25;

    public String[] getProductDataKey() {
        return ProductDataKey;
    }

    public Integer[] getIcDataID() {
        return IcDataID;
    }

    public int getIcCoinCost() {
        return icCoinCost;
    }

    public String[] getProductName() {
        return ProductName;
    }
}
