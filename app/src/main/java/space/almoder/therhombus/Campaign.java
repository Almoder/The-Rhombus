package space.almoder.therhombus;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import space.almoder.therhombus.support.RhombusData;

public class Campaign extends AppCompatActivity {
    static int levelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(RhombusData.getPreferenceManager(this).getInt("theme", R.style.Game));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);
        initializeButtons(getResources().getConfiguration().orientation);
    }

    private void initializeButtons(int o) {
        GridLayout grid = findViewById(R.id.gridLayout);
        boolean co = o == Configuration.ORIENTATION_PORTRAIT;
        grid.setRowCount(co ? 4 : 2);
        grid.setColumnCount(co ? 4 : 8);

        for (int i = 0; i < (co ? 4 : 2); i++) {
            for (int j = 0; j < (co ? 4 : 8); j++) {
                Button temp = new Button(this);
                temp.setId(co ? 2001 + i * 4 + j : 2001 + i * 8 + j);
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
                lp.height = 142;
                lp.width = 142;
                lp.setMargins(10, 10, 10, 10);
                TypedValue backValue = new TypedValue();
                TypedValue textValue = new TypedValue();
                Resources.Theme theme = getTheme();
                theme.resolveAttribute(R.attr.colorPrimary, backValue, true);
                theme.resolveAttribute(R.attr.colorOnPrimary, textValue, true);
                @ColorInt int backColor = backValue.data;
                @ColorInt int textColor = textValue.data;
                temp.setBackgroundColor(backColor);
                temp.setTextColor(textColor);
                lp.setGravity(Gravity.CENTER);
                temp.setLayoutParams(lp);
                int index = co ? i * 4 + j + 1 : i * 8 + j + 1;
                temp.setText(getResources().getString(
                        getResources().getIdentifier("cBT" + index, "string", getPackageName())));
                temp.setTextSize(getResources().getDimension(co ?
                        R.dimen.campaignButtonPortraitTextSize :
                        R.dimen.campaignButtonLandscapeTextSize));

                temp.setOnClickListener(getLevelOnClick(temp));
                grid.addView(temp);
            }
        }
    }

    View.OnClickListener getLevelOnClick(final Button button) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLevelId(button.getId());
                startGame();
            }
        };
    }

    public void startGame() {
        Intent intent = new Intent(this, Game.class);
        setLevelId(getLevelId() - 2000);
        intent.putExtra("levelId", levelId);
        intent.putExtra("image", getIntent().getIntExtra("image", R.drawable.cross));
        startActivity(intent);
    }

    public static int getLevelId() {
        return levelId;
    }

    public static void setLevelId(int levelId) {
        Campaign.levelId = levelId;
    }
}