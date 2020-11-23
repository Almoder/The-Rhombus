package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomGame extends AppCompatActivity {

    private int height = 3, width = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);
        Spinner spinner = findViewById(R.id.spinnerFieldForm);
        spinner.setSelection(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onStartCustomGameClick(View view) {
        Intent intent = new Intent(this, Game.class);
        Spinner spinner = findViewById(R.id.spinnerFieldForm);
        intent.putExtra("pveMode", true);
        intent.putExtra("form", spinner.getSelectedItemPosition() + 1);
        intent.putExtra("height", height);
        intent.putExtra("width", width);
        startActivity(intent);
    }

    public void onFormHeightInc(View view) {
        if (height < 16) {
            height++;
            refreshTextView();
        }
    }

    public void onFormHeightDec(View view) {
        if (height > 2) {
            height--;
            refreshTextView();
        }
    }

    public void onFormWidthInc(View view) {
        if (width < 16) {
            width++;
            refreshTextView();
        }
    }

    public void onFormWidthDec(View view) {
        if (width > 2) {
            width--;
            refreshTextView();
        }
    }

    private void refreshTextView() {
        TextView textView = findViewById(R.id.fieldFormHW);
        String s = height + ":" + width;
        textView.setText(s);
    }
}