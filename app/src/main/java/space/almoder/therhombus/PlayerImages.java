package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import space.almoder.therhombus.shop.ProductList;
import space.almoder.therhombus.support.RhombusData;

public class PlayerImages extends AppCompatActivity {

    ProductList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(RhombusData.getPreferenceManager(this).getInt("theme", R.style.Game));
        super.onCreate(savedInstanceState);
        list = new ProductList();
        setContentView(R.layout.activity_player_images);
        TableLayout tl = findViewById(R.id.playerImageTable);
        for (int i = 0; i < 4; i++) {
            TableRow tr = new TableRow(this);
            for (int j = 1; j <= 3; j++) {
                ImageView iv = new ImageView(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams();
                lp.width = (int)getResources().getDimension(R.dimen.playerImageViewHW);
                lp.height = (int)getResources().getDimension(R.dimen.playerImageViewHW);
                lp.setMargins(0, 0,
                        (int)getResources().getDimension(R.dimen.playerImagesTableMargin),
                        (int)getResources().getDimension(R.dimen.playerImagesTableMargin));
                iv.setLayoutParams(lp);
                iv.setId(2500 + j * 3 + i);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                if(RhombusData.isItComplete(list.getProductDataKey()[3*i+j-1], this))
                iv.setImageDrawable(ContextCompat.getDrawable(this,list.getIcDataID()[3*i+j-1]));
                else iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lock));
                iv.setOnClickListener(getImageViewListener(3*i+j-1));
                tr.addView(iv);
            }
            tl.addView(tr);
        }
    }

    View.OnClickListener getImageViewListener(int a) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RhombusData.isItComplete(list.getProductDataKey()[a], PlayerImages.this)) {
                    RhombusData.getPreferenceManager(PlayerImages.this)
                            .edit().putInt("playerImage",list.getIcDataID()[a]).apply();
                    System.out.println();
                    finish();
                }
            }
        };
    }
}