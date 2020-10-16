package space.almoder.therhombus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CompanyActivity extends AppCompatActivity {
    static int levelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            for (int i = 0; i < 4; i++) {
                TableRow row = new TableRow(this);
                TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER;
                row.setLayoutParams(params);
                for (int j = 0; j < 4; j++) {
                    Button temp = new Button(this);
                    temp.setId(2000 + (i + 1) * j);
                    temp.setWidth((int)getResources().getDimension(R.dimen.companyButtonPortraitSize));
                    temp.setHeight((int)getResources().getDimension(R.dimen.companyButtonPortraitSize));
                    temp.setBackgroundColor(getResources().getColor(R.color.companyActivityButtonBack));
                    temp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    temp.setText(getResources().getString(getResources().getIdentifier("cBT" + (i + 1) * (j + 1), "string", getPackageName())));
                    temp.setTextSize(getResources().getDimension(R.dimen.companyButtonPortraitTextSize));
                    temp.setTextColor(getResources().getColor(R.color.companyActivityButtonText));
                    temp.setOnClickListener(getLevelOnClick(temp));
                    row.addView(temp);
                }
                table.addView(row);
            }
        }
        else {
            for (int i = 0; i < 2; i++) {
                TableRow row = new TableRow(this);
                TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER;
                params.rightMargin = 0;
                row.setLayoutParams(params);
                for (int j = 0; j < 8; j++) {
                    Button temp = new Button(this);
                    temp.setId(2000 + (i + 1) * j);
                    temp.setWidth((int)getResources().getDimension(R.dimen.companyButtonLandscapeSize));
                    temp.setHeight((int)getResources().getDimension(R.dimen.companyButtonLandscapeSize));
                    temp.setBackgroundColor(getResources().getColor(R.color.companyActivityButtonBack));
                    temp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    temp.setText(getResources().getString(getResources().getIdentifier("cBT" + (i + 1) * (j + 1), "string", getPackageName())));
                    temp.setTextSize(getResources().getDimension(R.dimen.companyButtonLandscapeTextSize));
                    temp.setTextColor(getResources().getColor(R.color.companyActivityButtonText));
                    temp.setOnClickListener(getLevelOnClick(temp));
                    row.addView(temp);
                }
                table.addView(row);
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
        Intent intent = new Intent(this, GameActivity.class);
        setLevelId(getLevelId() - 2000);
        intent.putExtra("levelId", levelId);
        startActivity(intent);
    }

    public static int getLevelId() {
        return levelId;
    }

    public static void setLevelId(int levelId) {
        CompanyActivity.levelId = levelId;
    }

    public void onBackClick(View view) {
        finish();
    }
}