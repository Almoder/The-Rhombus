package space.almoder.therhombus;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {
    private int[][] lines;
    private int turn = 0, pOne = 0, pTwo = 0;
    private boolean addition = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        int lid = getIntent().getIntExtra("levelId", Campaign.getLevelId());

        if (savedInstanceState != null) {
            for (int i = 0; i < lines.length; i++) {
                lines[i] = savedInstanceState.getIntArray("lines" + i);
            }
            FieldBuilder fb = new FieldBuilder(new ResourceManager(this, lid), (TableLayout) findViewById(R.id.field), lines);
            fb.buildButtonField((TableLayout) findViewById(R.id.buttonField), getLineOnClick());
            addition = savedInstanceState.getBoolean("addition");
        }
        else {
            FieldBuilder fb = new FieldBuilder(new ResourceManager(this, lid), (TableLayout) findViewById(R.id.field));
            fb.buildButtonField((TableLayout) findViewById(R.id.buttonField), getLineOnClick());
            lines = fb.getLines();
        }
    }

    View.OnClickListener getLineOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                int ivId = (v.getId()) - 3300, rem = ivId % 10;
                ImageView ivLine = findViewById(3000 + ivId);
                ivLine.setBackgroundColor(getResources().getColor(turn == 0 ? R.color.p1Color : R.color.p2Color));
                lines[(ivId - rem) / 10][rem] = turn == 0 ? 1 : 2;
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
                if (turn == 1) {
                    letAITurn();
                }
            }
        };
    }

    private boolean checkLines() {
        boolean point = false;
        for(int i = 1; i <= lines.length - 2; i += 2) {
            for (int j = 1; j <= lines[i].length - 2; j += 2) {
                if (lines[i][j] > 0) continue;
                if (lines[i - 1][j] > 0 && lines[i][j - 1] > 0 && lines[i + 1][j] > 0 && lines[i][j + 1] > 0) {
                    ImageView iv = findViewById(3000 + i * 10 + j);
                    lines[i][j] = turn == 0 ? 1 : 2;
                    iv.setImageResource(lines[i][j] == 1 ? R.drawable.cross : R.drawable.circle);
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
        for (int i = 0; i < lines.length; i++)
            savedInstanceState.putIntArray("lines" + i, lines[i]);
        savedInstanceState.putBoolean("addition", addition);
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
                        iv = findViewById(3300 + i * 10 + j);
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
                        iv = findViewById(3300 + i * 10 + j);
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
                        iv = findViewById(3300 + i * 10 + j);
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
                        iv = findViewById(3300 + i * 10 + j);
                        iv.callOnClick();
                        return;
                    }
                }
            }
        }
        if (freeI != 0 && freeJ != 0) {
            iv = findViewById(3300 + freeI * 10 + freeJ);
            iv.callOnClick();
        }
    }
}