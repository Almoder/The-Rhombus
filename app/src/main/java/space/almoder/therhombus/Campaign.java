package space.almoder.therhombus;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

public class Campaign extends AppCompatActivity {
    static int levelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);
        initializeButtons(getResources().getConfiguration().orientation);
    }

    private void initializeButtons(int o) {
        TableLayout table = findViewById(R.id.tableLayout);
        boolean co = o == Configuration.ORIENTATION_PORTRAIT;
        for (int i = 0; i < (co ? 4 : 2); i++) {
            TableRow row = new TableRow(this);
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            row.setLayoutParams(params);
            for (int j = 0; j < (co ? 4 : 8); j++) {
                Button temp = new Button(this);
                temp.setId(2001 + i * 4 + j);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(
                        co ? TableRow.LayoutParams.WRAP_CONTENT :
                                (int)getResources().getDimension(R.dimen.campaignButtonLandscapeSize),
                        TableRow.LayoutParams.WRAP_CONTENT);
                temp.setLayoutParams(lp);
                temp.setBackgroundColor(getResources().getColor(R.color.campaignButtonBack));
                int index = co ? i * 4 + j + 1 : i * 8 + j + 1;
                temp.setText(getResources().getString(
                        getResources().getIdentifier("cBT" + index, "string", getPackageName())));
                temp.setTextSize(getResources().getDimension(co ? R.dimen.campaignButtonPortraitTextSize :
                        R.dimen.campaignButtonLandscapeTextSize));
                temp.setTextColor(getResources().getColor(R.color.campaignButtonText));
                temp.setOnClickListener(getLevelOnClick(temp));
                row.addView(temp);
            }
            table.addView(row);
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