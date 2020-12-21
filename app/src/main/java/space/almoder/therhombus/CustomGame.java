package space.almoder.therhombus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import space.almoder.therhombus.support.RhombusData;

public class CustomGame extends AppCompatActivity {

    private int height = 3, width = 3;
    int form = 0;
    private boolean mode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(RhombusData.getPreferenceManager(this).getInt("theme", R.style.Game));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);
        Spinner spinner = findViewById(R.id.spinnerFieldForm);
        spinner.setOnItemSelectedListener(onItemSelectedListener());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onStartCustomGameClick(View view) {
        Intent intent = new Intent(this, Game.class);
        Switch s = findViewById(R.id.switchAdditionTurn);
        intent.putExtra("pveMode", mode);
        intent.putExtra("addTurn", s.isChecked());
        intent.putExtra("form", form);
        intent.putExtra("height", height);
        intent.putExtra("width", width);
        startActivity(intent);
    }

    public void onFormHeightInc(View view) {
        if (height < 12) {
            Spinner spinner = findViewById(R.id.spinnerFieldForm);
            if (spinner.getSelectedItemPosition() == 1) {
                height += 2;
                width += 2;
            }
            else height++;
            refreshTextView();
        }
    }

    public void onFormHeightDec(View view) {
        if (height > 3) {
            Spinner spinner = findViewById(R.id.spinnerFieldForm);
            if (spinner.getSelectedItemPosition() == 1) {
                height -= 2;
                width -= 2;
            }
            else height--;
            refreshTextView();
        }
    }

    public void onFormWidthInc(View view) {
        if (width < 12) {
            Spinner spinner = findViewById(R.id.spinnerFieldForm);
            if (spinner.getSelectedItemPosition() == 1) {
                height += 2;
                width += 2;
            }
            else width++;
            refreshTextView();
        }
    }

    public void onFormWidthDec(View view) {
        if (width > 3) {
            Spinner spinner = findViewById(R.id.spinnerFieldForm);
            if (spinner.getSelectedItemPosition() == 1) {
                height -= 2;
                width -= 2;
            }
            else width--;
            refreshTextView();
        }
    }

    private Spinner.OnItemSelectedListener onItemSelectedListener() {
        return new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                form = position;
                height = width = 3;
                refreshTextView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setSelection(0);
            }
        };
    }

    private void refreshTextView() {
        TextView textView = findViewById(R.id.fieldFormHW);
        String s = height + ":" + width;
        textView.setText(s);
    }

    public void onPvpMode(View view) {
        mode = false;
    }

    public void onPveMode(View view) {
        mode = true;
    }
}