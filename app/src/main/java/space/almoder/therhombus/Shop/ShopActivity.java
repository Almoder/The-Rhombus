package space.almoder.therhombus.Shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import space.almoder.therhombus.R;

public class ShopActivity extends AppCompatActivity {

    RecyclerView ThemeListForShop;
    RecyclerView IcListForShop;
    ArrayList<Items_shop> themeList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ThemeListForShop = findViewById(R.id.shopView);
        fillShopList();
        RecyclerView.LayoutManager lr = new GridLayoutManager(this,2);
        ShopAdapter shopAdapter = new ShopAdapter(themeList, this);
        ThemeListForShop.setLayoutManager(lr);
        ThemeListForShop.setAdapter(shopAdapter);
        shopAdapter.notifyDataSetChanged();
    }

    private void fillShopList(){
        themeList.add(new Items_shop("Dark","25 coins", ContextCompat.getDrawable(this,R.drawable.i6)));
        themeList.add(new Items_shop("Light","25 coins", ContextCompat.getDrawable(this,R.drawable.i3)));
        themeList.add(new Items_shop("Dark","25 coins", ContextCompat.getDrawable(this,R.drawable.i6)));
        themeList.add(new Items_shop("Light","25 coins", ContextCompat.getDrawable(this,R.drawable.i3)));
        themeList.add(new Items_shop("Dark","25 coins", ContextCompat.getDrawable(this,R.drawable.i6)));
        themeList.add(new Items_shop("Light","25 coins", ContextCompat.getDrawable(this,R.drawable.i3)));
        themeList.add(new Items_shop("Dark","25 coins", ContextCompat.getDrawable(this,R.drawable.i6)));
        themeList.add(new Items_shop("Light","25 coins", ContextCompat.getDrawable(this,R.drawable.i3)));
    }
}