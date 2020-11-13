package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class PlayerImages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                iv.setImageResource(getResources().getIdentifier(
                        "i" + (j * 3 + i), "drawable", getPackageName()));
                iv.setOnClickListener(getImageViewListener());
                tr.addView(iv);
            }
            tl.addView(tr);
        }
    }

    View.OnClickListener getImageViewListener() {
        final Intent intent = new Intent(this, Settings.class);
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("imageID",(int)v.getId() - 2500);
                startActivity(intent);
            }
        };
    }
}