package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import space.almoder.therhombus.support.RhombusData;

public class Settings extends AppCompatActivity {

    ImageView piv;
    RadioGroup themeGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(RhombusData.getPreferenceManager(this).getInt("theme", R.style.Game));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        piv = findViewById(R.id.playerImageView);
        themeGroup = findViewById(R.id.themeGroup);
        setPositionForRadioButtons();
        themeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.dark) {
                    RhombusData.getPreferenceManager(Settings.this).edit()
                            .putInt("theme", R.style.DarkTheme1).apply();
                    Intent intent = new Intent(Settings.this, Threshold.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    startActivity(new Intent(Settings.this, MainMenu.class));
                    startActivity(getIntent());
                }
               if(checkedId == R.id.extra) {
                   RhombusData.getPreferenceManager(Settings.this).edit()
                           .putInt("theme", R.style.ExtraBlack).apply();
               }
               if(checkedId == R.id.base) {
                    RhombusData.getPreferenceManager(Settings.this).edit()
                            .putInt("theme", R.style.Game).apply();
                }
                if(checkedId == R.id.nion) {
                    RhombusData.getPreferenceManager(Settings.this).edit()
                            .putInt("theme", R.style.Nion).apply();
                }
                Intent intent = new Intent(Settings.this, Threshold.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                startActivity(new Intent(Settings.this, MainMenu.class));
                startActivity(getIntent());
            }
        });
    }

    private void setPositionForRadioButtons(){
        int i = RhombusData.getPreferenceManager(this).getInt("theme", R.style.Game);
        if(i == R.style.Game)
            themeGroup.check(R.id.base);
        if(i == R.style.DarkTheme1)
            themeGroup.check(R.id.dark);
        if(i == R.style.ExtraBlack)
            themeGroup.check(R.id.extra);
        if(i == R.style.Nion)
            themeGroup.check(R.id.nion);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void playerImageViewOnClick(View view) {
        startActivity(new Intent(this, PlayerImages.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        piv.setImageDrawable(ContextCompat.getDrawable(this, RhombusData
                .getPreferenceManager(this).getInt("playerImage", R.drawable.circle)));
    }
}