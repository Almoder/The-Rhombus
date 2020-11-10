package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        LinearLayout ll = findViewById(R.id.playerImages);
        for (int i = 1; i <= 12; i++) {
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    (int) getResources().getDimension(R.dimen.settingsPlayerImagesWH),
                    (int) getResources().getDimension(R.dimen.settingsPlayerImagesWH));
            lp.setMarginEnd((int)getResources().getDimension(R.dimen.settingsPlayerImagesPadding));
            iv.setLayoutParams(lp);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(getResources().getIdentifier("i" + i, "drawable", getPackageName()));
            ll.addView(iv);
        }
    }
}