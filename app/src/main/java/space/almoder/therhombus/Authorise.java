package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Authorise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorise);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void acceptButtonClick(View view) {
        startActivity(new Intent(this, MainMenu.class));
        finish();
    }
}