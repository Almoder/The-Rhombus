package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import space.almoder.therhombus.shop.ShopActivity;
import space.almoder.therhombus.support.RhombusData;

public class MainMenu extends AppCompatActivity {
    TextView coinCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(RhombusData.getPreferenceManager(this).getInt("theme", R.style.Game));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        coinCount = findViewById(R.id.coinsCount);
    }

    public void onMenuClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.mb1:
                intent = new Intent(this, Campaign.class);
                intent.putExtra("image", getIntent().getIntExtra("image", R.drawable.cross));
                startActivity(intent);
                break;
            case R.id.mb2:
                intent = new Intent(this, CustomGame.class);
                startActivity(intent);
                break;
            case R.id.mb3:
                startActivityForResult(new Intent(this, Settings.class), 0);
                break;
            case R.id.mb4:
                intent = getIntent();
                setResult(RESULT_OK, intent);
                this.finish();
                break;
            case R.id.mb5:
                intent = new Intent(this, ShopActivity.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if(resultCode != Activity.RESULT_CANCELED) {
                    finish();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, getIntent());
        super.onBackPressed();
    }

    public void addOneCoin(View view) {
        RhombusData.addCoin(1, this);
        RhombusData.getCoinsCount(this)
                .delay(300, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getObserverForCoins());
    }

    private Observer<Integer> getObserverForCoins() {
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

    @Override
    protected void onResume() {
        super.onResume();
        RhombusData.getCoinsCount(this).subscribe(getObserverForCoins());
    }
}