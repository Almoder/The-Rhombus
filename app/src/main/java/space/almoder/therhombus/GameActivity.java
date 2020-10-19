package space.almoder.therhombus;

import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private int[][] lines;
    private int lid, turn = 0, pOne = 0, pTwo = 0;
    private boolean addition = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        lid = getIntent().getIntExtra("levelId", CompanyActivity.getLevelId());
        int rows = getResources().getInteger(getResources().getIdentifier("field" + lid + "rows", "integer", getPackageName()));
        int cols = getResources().getInteger(getResources().getIdentifier("field" + lid + "cols", "integer", getPackageName()));
        int[][] indexes = new int[rows][cols];
        lines = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            indexes[i] = getResources().getIntArray(getResources().getIdentifier("f" + lid + "_" + (i + 1), "array", getPackageName()));
        }
        TextView tv1 = findViewById(R.id.pOnePTV);
        TextView tv2 = findViewById(R.id.pTwoPTV);
        tv1.setText(String.valueOf(pOne));
        tv2.setText(String.valueOf(pTwo));
        TableLayout field = findViewById(R.id.field);
        int /*eDot, eLnh, eLnv, eRct,*/ fDot, fLnh, fLnv, fRct, bnHl, bnVl;
        if (getResources().getInteger(getResources().getIdentifier("field" + lid + "colors", "integer", getPackageName())) == 0) {
            /*
            eDot = getResources().getColor(R.color.eDot);
            eLnh = getResources().getColor(R.color.eLnh);
            eLnv = getResources().getColor(R.color.eLnv);
            eRct = getResources().getColor(R.color.eRct);
             */
            fDot = getResources().getColor(R.color.fDot);
            fLnh = getResources().getColor(R.color.fLnh);
            fLnv = getResources().getColor(R.color.fLnv);
            fRct = getResources().getColor(R.color.fRct);
            bnHl = getResources().getColor(R.color.bnHl);
            bnVl = getResources().getColor(R.color.bnVl);
        }
        else {
            /*
            eDot = getResources().getColor(getResources().getIdentifier("eDot" + lid, "color", getPackageName()));
            eLnh = getResources().getColor(getResources().getIdentifier("eLnh" + lid, "color", getPackageName()));
            eLnv = getResources().getColor(getResources().getIdentifier("eLnv" + lid, "color", getPackageName()));
            eRct = getResources().getColor(getResources().getIdentifier("eRct" + lid, "color", getPackageName()));
             */
            fDot = getResources().getColor(getResources().getIdentifier("fDot" + lid, "color", getPackageName()));
            fLnh = getResources().getColor(getResources().getIdentifier("fLnh" + lid, "color", getPackageName()));
            fLnv = getResources().getColor(getResources().getIdentifier("fLnv" + lid, "color", getPackageName()));
            fRct = getResources().getColor(getResources().getIdentifier("fRct" + lid, "color", getPackageName()));
            bnHl = getResources().getColor(getResources().getIdentifier("bnHl" + lid, "color", getPackageName()));
            bnVl = getResources().getColor(getResources().getIdentifier("bnVl" + lid, "color", getPackageName()));
        }
        for (int i = 0; i < rows; i++) {
            TableRow tr = new TableRow(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            tr.setLayoutParams(params);
            int minWD = (int)getResources().getDimension(getResources().getIdentifier("f" + lid + "minWD", "dimen", getPackageName()));
            int maxWD = (int)getResources().getDimension(getResources().getIdentifier("f" + lid + "maxWD", "dimen", getPackageName()));
            for (int j = 0; j < cols; j++) {
                ImageView iv = new ImageView(this);
                iv.setId(3000 + i * 10 + j);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                TableRow.LayoutParams ivParams;
                switch (indexes[i][j]) {
                    case 0:
                        //iv.setBackgroundColor(eDot);
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(minWD, minWD);
                        break;
                    case 1:
                        //iv.setBackgroundColor(eLnh);
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(maxWD, minWD);
                        break;
                    case 2:
                        //iv.setBackgroundColor(eLnv);
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(minWD, maxWD);
                        break;
                    case 3:
                        //iv.setBackgroundColor(eRct);
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(maxWD, maxWD);
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
                        iv.setOnClickListener(getLineOnClick(iv));
                        ivParams = new TableRow.LayoutParams(maxWD, minWD);
                        break;
                    case 9:
                        iv.setBackgroundColor(bnVl);
                        iv.setOnClickListener(getLineOnClick(iv));
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
        if (savedInstanceState != null) {
            for (int i = 0; i < rows; i++) lines[i] = savedInstanceState.getIntArray("lines" + i);
            for(int i = 1; i <= lines.length - 2; i += 2)
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
            for (int i = 0; i < lines.length; i++) {
                for (int j = 0; j < lines[i].length; j++) {
                    if (lines[i][j] == 1 && (indexes[i][j] == 8 || indexes[i][j] == 9)) {
                        ImageView iv = (ImageView) findViewById(3000 + i * 10 + j);
                        getLineOnClick(iv).onClick(iv);
                    }
                }
            }

        }
    }

    View.OnClickListener getLineOnClick(final ImageView iv) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setBackgroundColor(getResources().getColor(turn == 0 ? R.color.player1Color : R.color.player2Color));
                iv.setEnabled(false);
                int ivId = (int)iv.getId() - 3000, rem = ivId % 10;
                lines[(ivId - rem) / 10][rem] = 1;
                boolean check = checkLines();
                if (check) {
                    if (addition) {
                        addition = false;
                        turn = (turn == 0 ? 1 : 0);
                    }
                    else addition = true;
                }
                else {
                    addition = false;
                    turn = (turn == 0 ? 1 : 0);
                }
            }
        };
    }

    public void onPauseClick(View view) {

    }

    private boolean checkLines() {
        boolean point = false;
        for(int i = 1; i <= lines.length - 2; i += 2) {
            for (int j = 1; j <= lines[i].length - 2; j += 2) {
                if (lines[i][j] > 0) continue;
                if (lines[i - 1][j] == 1 && lines[i][j - 1] == 1 && lines[i + 1][j] == 1 && lines[i][j + 1] == 1) {
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
    }
}