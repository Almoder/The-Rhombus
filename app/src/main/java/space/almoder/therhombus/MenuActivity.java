package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onButton1Click(View view) {
        startActivity(new Intent(this, CompanyActivity.class));
    }

    public void onButton2Click(View view) {

    }

    public void onButton3Click(View view) {

    }

    public void onButton4Click(View view) {
        MainActivity.setFin(true);
        finish();
    }
}