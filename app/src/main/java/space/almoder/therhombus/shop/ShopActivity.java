package space.almoder.therhombus.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import space.almoder.therhombus.R;
import space.almoder.therhombus.support.RhombusData;

public class ShopActivity extends AppCompatActivity {

    RecyclerView ThemeListForShop;
    TextView coinCount;
    ProductList productList;
    ArrayList<Items_shop> themeList = new ArrayList<>();
    ShopAdapter shopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(RhombusData.getPreferenceManager(this).getInt("theme", R.style.Game));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        productList = new ProductList();
        ThemeListForShop = findViewById(R.id.shopView);
        coinCount = findViewById(R.id.coinsCount);
        RhombusData.getCoinsCount(this).subscribe(getObserverForCoins());
        shopAdapter = new ShopAdapter(themeList, this);
        fillShopList();
        RecyclerView.LayoutManager lr = new GridLayoutManager(this,2);

        ThemeListForShop.setLayoutManager(lr);
        ThemeListForShop.setAdapter(shopAdapter);
        ThemeListForShop.getAdapter();
        shopAdapter.notifyDataSetChanged();

    }

    private void fillShopList(){
        ProductList productList = new ProductList();
        for(int i = 0; i < productList.getProductDataKey().length; i++) {
            RhombusData.wasThisItemPurchased(productList.getProductDataKey()[i],this).subscribe(getObserverForPurchaseStatus(i));
        }
    }
    private Observer<Integer> getObserverForCoins(){
        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onNext(Integer integer) {
                if (integer != null) {
                    coinCount.setText(integer.toString());
                }
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        return observer;
    }
    private Observer<Boolean> getObserverForPurchaseStatus(int position){
        final Observer<Boolean> observer = new Observer<Boolean>() {
            @Override
            public void onNext(Boolean integer) {
                if (integer != null) {
                        if(!integer) {
                            themeList.add(new Items_shop(productList.getProductName()[position], String.valueOf(productList.getIcCoinCost()),
                                    ContextCompat.getDrawable(ShopActivity.this, productList.getIcDataID()[position]),
                                    productList.getProductDataKey()[position]));
                            ThemeListForShop.setAdapter(shopAdapter);
                            shopAdapter.notifyDataSetChanged();
                        }
                }
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        return observer;
    }
}