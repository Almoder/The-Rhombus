package space.almoder.therhombus.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import androidx.core.content.ContextCompat;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import space.almoder.therhombus.R;
import space.almoder.therhombus.shop.ShopActivity;

public class RhombusData {

    public static SharedPreferences getPreferenceManager(Context context){
        return  PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void addCoin(int receivedCoins, Context context){
        Thread addCoinThread = new Thread(()->{ int currentCoinsCount =  getPreferenceManager(context).getInt("Coins", 0);
        currentCoinsCount +=receivedCoins;
        getPreferenceManager(context).edit().putInt("Coins", currentCoinsCount).apply();});
        addCoinThread.start();
    }
    public static void writeOffCoins(int coinsCount, Context context){
        Thread writeOffCoins = new Thread(()->{
            int currentCoinsCount =  getPreferenceManager(context).getInt("Coins", 0);
            System.out.println("Было: "+ currentCoinsCount);
            currentCoinsCount -=coinsCount;
            System.out.println("Осталось: "+ currentCoinsCount);
            getPreferenceManager(context).edit().putInt("Coins", currentCoinsCount).apply();});
            writeOffCoins.start();
    }

    public static void markThisItemAsPurchased(String productKey, Context context){
        Thread markThread = new Thread(()->{ getPreferenceManager(context).edit().putBoolean(productKey,true).apply();});
        markThread.start();
    }

    public static Observable<Integer> getCoinsCount(Context context){
       Observable<Integer> Coins = Observable.create((ObservableOnSubscribe<Integer>)(subscriber -> {
            Thread getCoinThread = new Thread((() -> {
                System.out.println(getPreferenceManager(context).getInt("Coins", 0));
                subscriber.onNext( getPreferenceManager(context).getInt("Coins", 0));
                subscriber.onComplete();
            }));
            getCoinThread.start();
        }));
        return Coins.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Boolean> wasThisItemPurchased (String productKey, Context context) {
        Observable<Boolean> Coins = Observable.create((ObservableOnSubscribe<Boolean>) (subscriber -> {
            Thread getCoinThread = new Thread(() -> {
                subscriber.onNext(getPreferenceManager(context).getBoolean(productKey, false));
                subscriber.onComplete();});
            getCoinThread.start();
        }));
        return Coins.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Drawable getPlayerIcDrawable(Context context){
        return  ContextCompat.getDrawable(context, RhombusData
                .getPreferenceManager(context).getInt("playerImage", R.drawable.cross));
    }

    public static Integer getPlayerIcID(Context context){
        return  RhombusData.getPreferenceManager(context).getInt("playerImage", R.drawable.cross);
    }

    @Deprecated
    public static Boolean isItComplete(String Key, Context context){
        System.out.println(Key +" "+getPreferenceManager(context).getBoolean(Key, false));
        return getPreferenceManager(context).getBoolean(Key, false);
    }
    @Deprecated
    public static void markItAsComplete(String Key, Context context){
        getPreferenceManager(context).edit().putBoolean(Key, true).apply();
    }

}
