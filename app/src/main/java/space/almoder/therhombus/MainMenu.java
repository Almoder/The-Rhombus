package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        LinearLayout ll = findViewById(R.id.linearLayout);
        for (int i = 1; i < 5; i++) {
            Button button = new Button(this);
            button.setId(1000 + i);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(lp);
            button.setText(getResources().getString(
                    getResources().getIdentifier(
                            "mainMenuButton" + i,
                            "string", getPackageName())));
            button.setTextSize(getResources().getDimension(R.dimen.mainMenuButtonTextSize));
            button.setTextColor(getResources().getColor(R.color.mainMenuButtonText));
            button.setBackgroundColor(getResources().getColor(R.color.mainMenuButtonBack));
            button.setOnClickListener(getButtonOnClick(button));
            ll.addView(button);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Threshold.class));
    }

    private View.OnClickListener getButtonOnClick(final Button button) {
        final Intent intent;
        switch (button.getId()) {
            case 1001:
                intent = new Intent(this, Campaign.class);
                intent.putExtra("image", getIntent().getIntExtra("image", R.drawable.cross));
                break;
            case 1002:
                intent = new Intent(this, CustomGame.class);
                break;
            case 1003:
                intent = new Intent(this, Settings.class);
                break;
            default:
                Threshold.setFin(true);
                intent = null;
                break;
        }
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent != null) startActivity(intent);
                else finish();
            }
        };
    }
}