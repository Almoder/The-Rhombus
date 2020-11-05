package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Threshold extends AppCompatActivity {
    static boolean fin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threshold);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getFin()) {
            setFin(false);
            Threshold.this.finish();
        }
    }

    public void startButtonClick(View view) {
        startActivity(new Intent(this, Authorise.class));
    }

    public static boolean getFin() {
        return fin;
    }

    public static void setFin(boolean fin) {
        Threshold.fin = fin;
    }
}
