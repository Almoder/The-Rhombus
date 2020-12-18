package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import space.almoder.therhombus.support.RhombusData;

public class CustomGame extends AppCompatActivity {

    private int height = 3, width = 3, form = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(RhombusData.getPreferenceManager(this).getInt("theme", R.style.Game));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);
        Spinner spinner = findViewById(R.id.spinnerFieldForm);
        //spinner.setSelection(0);
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
        intent.putExtra("pveMode", true);
        intent.putExtra("addTurn", s.isChecked());
        intent.putExtra("form", form);
        intent.putExtra("height", height);
        intent.putExtra("width", width);
        Toast.makeText(this, "form = " + form, Toast.LENGTH_SHORT);
        startActivity(intent);
    }

    public void onFormHeightInc(View view) {
        if (height < 9) {
            Spinner spinner = findViewById(R.id.spinnerFieldForm);
            if (spinner.getSelectedItemPosition() == 1) {
                height += 2;
                width += 2;
            }
            else if (spinner.getSelectedItemPosition() == 2) {
                height++;
                width++;
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
            else if (spinner.getSelectedItemPosition() == 2) {
                height--;
                width--;
            }
            else height--;
            refreshTextView();
        }
    }

    public void onFormWidthInc(View view) {
        if (width < 7) {
            Spinner spinner = findViewById(R.id.spinnerFieldForm);
            if (spinner.getSelectedItemPosition() == 1) {
                height += 2;
                width += 2;
            }
            else if (spinner.getSelectedItemPosition() == 2) {
                height++;
                width++;
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
            else if (spinner.getSelectedItemPosition() == 2) {
                height--;
                width--;
            }
            else width--;
            refreshTextView();
        }
    }

    private Spinner.OnItemSelectedListener onItemSelectedListener() {
        return new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
        Spinner spinner = findViewById(R.id.spinnerFieldForm);
        form = spinner.getSelectedItemPosition();
        TextView textView = findViewById(R.id.fieldFormHW);
        String s = height + ":" + width;
        textView.setText(s);
    }
}