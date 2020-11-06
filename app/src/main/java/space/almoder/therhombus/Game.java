package space.almoder.therhombus;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
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
        int rows = getResources().getInteger(getResources().getIdentifier("field" + lid + "rows", "integer", getPackageName()));
        int cols = getResources().getInteger(getResources().getIdentifier("field" + lid + "cols", "integer", getPackageName()));
        int[][] indexes = new int[rows][cols];
        lines = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            indexes[i] = getResources().getIntArray(getResources().getIdentifier("f" + lid + "_" + (i + 1), "array", getPackageName()));
        }
        TableLayout field = findViewById(R.id.field);
        int fDot, fLnh, fLnv, fRct, bnHl, bnVl;
        if (getResources().getInteger(getResources().getIdentifier("field" + lid + "colors", "integer", getPackageName())) == 0) {
            fDot = getResources().getColor(R.color.fDot);
            fLnh = getResources().getColor(R.color.fLnh);
            fLnv = getResources().getColor(R.color.fLnv);
            fRct = getResources().getColor(R.color.fRct);
            bnHl = getResources().getColor(R.color.bnHl);
            bnVl = getResources().getColor(R.color.bnVl);
        }
        else {
            fDot = getResources().getColor(getResources().getIdentifier("fDot" + lid, "color", getPackageName()));
            fLnh = getResources().getColor(getResources().getIdentifier("fLnh" + lid, "color", getPackageName()));
            fLnv = getResources().getColor(getResources().getIdentifier("fLnv" + lid, "color", getPackageName()));
            fRct = getResources().getColor(getResources().getIdentifier("fRct" + lid, "color", getPackageName()));
            bnHl = getResources().getColor(getResources().getIdentifier("bnHl" + lid, "color", getPackageName()));
            bnVl = getResources().getColor(getResources().getIdentifier("bnVl" + lid, "color", getPackageName()));
        }
        int minWD = (int)getResources().getDimension(getResources().getIdentifier("f" + lid + "minWD", "dimen", getPackageName()));
        int maxWD = (int)getResources().getDimension(getResources().getIdentifier("f" + lid + "maxWD", "dimen", getPackageName()));
        for (int i = 0; i < rows; i++) {
            TableRow tr = new TableRow(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            tr.setLayoutParams(params);
            for (int j = 0; j < cols; j++) {
                ImageView iv = new ImageView(this);
                iv.setId(3000 + i * 10 + j);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                TableRow.LayoutParams ivParams;
                switch (indexes[i][j]) {
                    case 0:
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(minWD, minWD);
                        lines[i][j] = -1;
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(maxWD, minWD);
                        lines[i][j] = -1;
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(minWD, maxWD);
                        lines[i][j] = -1;
                        break;
                    case 3:
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(maxWD, maxWD);
                        lines[i][j] = -1;
                        break;
                    case 4:
                        iv.setBackgroundColor(fDot);
                        ivParams = new TableRow.LayoutParams(minWD, minWD);
                        lines[i][j] = 1;
                        break;
                    case 5:
                        iv.setBackgroundColor(fLnh);
                        ivParams = new TableRow.LayoutParams(maxWD, minWD);
                        lines[i][j] = 1;
                        break;
                    case 6:
                        iv.setBackgroundColor(fLnv);
                        ivParams = new TableRow.LayoutParams(minWD, maxWD);
                        lines[i][j] = 1;
                        break;
                    case 7:
                        iv.setBackgroundColor(fRct);
                        ivParams = new TableRow.LayoutParams(maxWD, maxWD);
                        break;
                    case 8:
                        iv.setBackgroundColor(bnHl);
                        ivParams = new TableRow.LayoutParams(maxWD, minWD);
                        break;
                    case 9:
                        iv.setBackgroundColor(bnVl);
                        ivParams = new TableRow.LayoutParams(minWD, maxWD);
                        break;
                    default:
                        ivParams = new TableRow.LayoutParams(minWD, minWD);
                        break;
                }
                iv.setLayoutParams(ivParams);
                tr.addView(iv);
            }
            field.addView(tr);
        }
        TableLayout buttonField = findViewById(R.id.buttonField);
        for (int i = 1; i < rows - 1; i++) {
            TableRow tr = new TableRow(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            tr.setLayoutParams(params);
            for (int j = 1; j < cols - 1; j++) {
                ImageView iv = new ImageView(this);
                iv.setId(3300 + i * 10 + j);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setImageResource(R.drawable.empty);
                TableRow.LayoutParams ivParams = new TableRow.LayoutParams((minWD + maxWD) / 2, (minWD + maxWD) / 2);
                iv.setLayoutParams(ivParams);
                if (indexes[i][j] == 8 || indexes[i][j] == 9) iv.setOnClickListener(getLineOnClick(iv));
                tr.addView(iv);
            }
            buttonField.addView(tr);
        }
        if (savedInstanceState != null) {
            for (int i = 0; i < rows; i++) {
                lines[i] = savedInstanceState.getIntArray("lines" + i);
            }
            for (int i = 1; i <= lines.length - 2; i += 2) {
                if (lines[i] == null) throw new NullPointerException();
                for (int j = 1; j <= lines[i].length - 2; j += 2) {
                    int index = lines[i][j];
                    if (index > 0) {
                        ImageView iv = findViewById(3000 + i * 10 + j);
                        iv.setImageResource(index == 1 ? R.drawable.cross : R.drawable.circle);
                        if (index == 1) {
                            pOne++;
                            TextView tv = findViewById(R.id.pOnePTV);
                            tv.setText(String.valueOf(pOne));
                        }
                        if (index == 2) {
                            pTwo++;
                            TextView tv = findViewById(R.id.pTwoPTV);
                            tv.setText(String.valueOf(pTwo));
                        }
                    }
                }
            }
            for (int i = 0; i < lines.length; i++) {
                if (lines[i] == null) throw new NullPointerException();
                for (int j = 0; j < lines[i].length; j++) {
                    if (lines[i][j] > 0 && (indexes[i][j] == 8 || indexes[i][j] == 9)) {
                        ImageView iv = findViewById(3000 + i * 10 + j);
                        iv.setBackgroundColor(getResources().getColor(lines[i][j] == 1 ? R.color.player1Color : R.color.player2Color));
                        ImageView ivButton = findViewById(3300 + i * 10 + j);
                        ivButton.setEnabled(false);
                    }
                }
            }
            addition = savedInstanceState.getBoolean("addition");
        }
    }

    View.OnClickListener getLineOnClick(final ImageView iv) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setEnabled(false);
                int ivId = (iv.getId()) - 3300, rem = ivId % 10;
                ImageView ivLine = findViewById(3000 + ivId);
                ivLine.setBackgroundColor(getResources().getColor(turn == 0 ? R.color.player1Color : R.color.player2Color));
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