package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageView piv = findViewById(R.id.playerImageView);
        piv.setImageResource(getResources().getIdentifier(
                "i" + getIntent().getIntExtra("imageID", 1),
                "drawable", getPackageName()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainMenu.class));
    }

    public void playerImageViewOnClick(View view) {
        startActivity(new Intent(this, PlayerImages.class));
    }

}