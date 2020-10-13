package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static boolean fin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (fin==true) MainActivity.this.finish();
    }

    public static boolean fin() {
        return fin;
    }

    public static void setFin(boolean fin) {
        MainActivity.fin = fin;
    }

    public void startButtonClick(View view) {
        startActivity(new Intent(this, MenuActivity.class));
    }
}
