package space.almoder.therhombus;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import space.almoder.therhombus.support.RhombusData;

public class Game extends AppCompatActivity {
    private int[][] lines;
    private int turn = 0, pOne = 0, pTwo = 0, image;
    private boolean addition = false, addMode = true, pveMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(RhombusData.getPreferenceManager(this).getInt("theme", R.style.Game));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        image = RhombusData.getPlayerIcID(this);
        pveMode = getIntent().getBooleanExtra("pveMode", true);
        addMode = getIntent().getBooleanExtra("addTurn", true);
        int lid = getIntent().getIntExtra("levelId", 0);
        if (lid != 0) {
            if (savedInstanceState != null) {
                lines = new int[savedInstanceState.getInt("linesLength")][];
                for (int i = 0; i < lines.length; i++) {
                    lines[i] = savedInstanceState.getIntArray("lines" + i);
                }
                FieldBuilder fb = new FieldBuilder(
                        new ResourceManager(this, lid),
                        (TableLayout) findViewById(R.id.field),
                        lines);
                fb.buildButtonField((TableLayout) findViewById(R.id.buttonField), getLineOnClick());
                addition = savedInstanceState.getBoolean("addition");
                pOne = savedInstanceState.getInt("pOne");
                pTwo = savedInstanceState.getInt("pTwo");
                TextView tv1 = findViewById(R.id.pOnePTV);
                TextView tv2 = findViewById(R.id.pTwoPTV);
                tv1.setText(String.valueOf(pOne));
                tv2.setText(String.valueOf(pTwo));
            }
            else {
                FieldBuilder fb = new FieldBuilder(
                        new ResourceManager(this, lid),
                        (TableLayout) findViewById(R.id.field));
                fb.buildButtonField((TableLayout) findViewById(R.id.buttonField), getLineOnClick());
                lines = fb.getLines();
            }
        }
        else {
            int form = getIntent().getIntExtra("form", 1);
            int height = getIntent().getIntExtra("height", 5);
            int width = getIntent().getIntExtra("width", 5);
            if (savedInstanceState != null) {
                lines = new int[savedInstanceState.getInt("linesLength")][];
                for (int i = 0; i < lines.length; i++) {
                    lines[i] = savedInstanceState.getIntArray("lines" + i);
                }
                FieldBuilder fb = new FieldBuilder(
                    new ResourceManager(this, form, width, height),
                    (TableLayout) findViewById(R.id.field),
                    lines);
                fb.buildButtonField((TableLayout) findViewById(R.id.buttonField), getLineOnClick());
                addition = savedInstanceState.getBoolean("addition");
                pOne = savedInstanceState.getInt("pOne");
                pTwo = savedInstanceState.getInt("pTwo");
                TextView tv1 = findViewById(R.id.pOnePTV);
                TextView tv2 = findViewById(R.id.pTwoPTV);
                tv1.setText(String.valueOf(pOne));
                tv2.setText(String.valueOf(pTwo));
            }
            else {
                FieldBuilder fb = new FieldBuilder(new ResourceManager(
                    this, form, width, height),
                    (TableLayout) findViewById(R.id.field));
                fb.buildButtonField((TableLayout) findViewById(R.id.buttonField), getLineOnClick());
                lines = fb.getLines();
            }
        }
    }

    View.OnClickListener getLineOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                int ivId = (v.getId()) - 3300, rem = ivId % lines[0].length;
                ImageView ivLine = findViewById(3000 + ivId);
                ivLine.setBackgroundColor(
                        getResources().getColor(turn == 0 ? R.color.p1Color : R.color.p2Color));
                lines[(ivId - rem) / lines[0].length][rem] = turn == 0 ? 1 : 2;
                boolean check = checkLines();
                if (check) {
                    if (addition) {
                        addition = false;
                        turn = turn == 0 ? 1 : 0;
                    }
                    else addition = true;
                }
                else {
                    addition = false;
                    turn = turn == 0 ? 1 : 0;
                }
                if (turn == 1 && pveMode) {
                    letAITurn();
                }
            }
        };
    }

    private boolean checkLines() {
        boolean point = false;
        for(int i = 1; i < lines.length - 1; i += 2) {
            for (int j = 1; j < lines[i].length - 1; j += 2) {
                if (lines[i][j] > 0) continue;
                if (lines[i - 1][j] > 0 && lines[i][j - 1] > 0 &&
                    lines[i + 1][j] > 0 && lines[i][j + 1] > 0) {
                    ImageView iv = findViewById(3000 + i * lines[i].length + j);
                    lines[i][j] = turn == 0 ? 1 : 2;
                    iv.setImageResource(lines[i][j] == 1 ? image : R.drawable.circle);
                    if (turn == 0) {
                        pOne++;
                        TextView tv = findViewById(R.id.pOnePTV);
                        tv.setText(String.valueOf(pOne));
                    }
                    else {
                        pTwo++;
                        TextView tv = findViewById(R.id.pTwoPTV);
                        tv.setText(String.valueOf(pTwo));
                    }
                    point = true;
                }
            }
        }
        return point;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("linesLength", lines.length);
        for (int i = 0; i < lines.length; i++)
            savedInstanceState.putIntArray("lines" + i, lines[i]);
        savedInstanceState.putBoolean("addition", addition);
        savedInstanceState.putInt("pOne", pOne);
        savedInstanceState.putInt("pTwo", pTwo);
    }

    private void letAITurn() {
        ImageView iv;
        int freeI = 0, freeJ = 0;
        for (int i = 1; i <= lines.length - 2; i += 2) {
            for (int j = 2; j <= lines[i].length - 3; j += 2) {
                if (lines[i][j] == 0) {
                    if (freeI == 0 && freeJ == 0) {
                        freeI = i;
                        freeJ = j;
                    }
                    if ((lines[i-1][j-1] > 0 && lines[i][j-2] > 0 && lines[i+1][j-1] > 0) ||
                        (lines[i-1][j+1] > 0 && lines[i][j+2] > 0 && lines[i+1][j+1] > 0)) {
                        iv = findViewById(3300 + i * lines[0].length + j);
                        iv.callOnClick();
                        return;
                    }
                }
            }
        }
        for (int i = 2; i <= lines.length - 3; i += 2) {
            for (int j = 1; j <= lines[i].length - 2; j += 2) {
                if (lines[i][j] == 0) {
                    if (freeI == 0 && freeJ == 0) {
                        freeI = i;
                        freeJ = j;
                    }
                    if ((lines[i-1][j-1] > 0 && lines[i-2][j] > 0 && lines[i-1][j+1] > 0) ||
                        (lines[i+1][j-1] > 0 && lines[i+2][j] > 0 && lines[i+1][j+1] > 0)) {
                        iv = findViewById(3300 + i * lines[0].length + j);
                        iv.callOnClick();
                        return;
                    }
                }
            }
        }
        for (int i = 1; i < lines.length - 2; i += 2) {
            for (int j = 2; j < lines[i].length - 1; j += 2) {
                if (lines[i][j] == 0) {
                    if (lines[i-1][j-1] == 0 && lines[i-1][j+1] == 0 &&
                        lines[i+1][j-1] == 0 && lines[i+1][j+1] == 0) {
                        iv = findViewById(3300 + i * lines[0].length + j);
                        iv.callOnClick();
                        return;
                    }
                }
            }
        }
        for (int i = 2; i < lines.length - 1; i += 2) {
            for (int j = 1; j < lines[i].length - 2; j += 2) {
                if (lines[i][j] == 0) {
                    if (lines[i-1][j-1] == 0 && lines[i+1][j-1] == 0 &&
                        lines[i-1][j+1] == 0 && lines[i+1][j+1] == 0) {
                        iv = findViewById(3300 + i * lines[0].length + j);
                        iv.callOnClick();
                        return;
                    }
                }
            }
        }
        if (freeI != 0 && freeJ != 0) {
            iv = findViewById(3300 + freeI * lines[0].length + freeJ);
            iv.callOnClick();
        }
    }
}