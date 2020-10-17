package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;

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
        TextView tv1 = (TextView)findViewById(R.id.pOnePTV);
        TextView tv2 = (TextView)findViewById(R.id.pTwoPTV);
        tv1.setText(String.valueOf(pOne));
        tv2.setText(String.valueOf(pTwo));
        TableLayout field = (TableLayout)findViewById(R.id.field);
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
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(minWD, minWD);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(maxWD, minWD);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(minWD, maxWD);
                        break;
                    case 3:
                    case 7:
                        iv.setImageResource(R.drawable.empty);
                        ivParams = new TableRow.LayoutParams(maxWD, maxWD);
                        break;
                    case 4:
                        iv.setBackgroundColor(getResources().getColor(R.color.shapeDefault));
                        ivParams = new TableRow.LayoutParams(minWD, minWD);
                        lines[i][j] = 1;
                        break;
                    case 5:
                        iv.setBackgroundColor(getResources().getColor(R.color.shapeDefault));
                        ivParams = new TableRow.LayoutParams(maxWD, minWD);
                        lines[i][j] = 1;
                        break;
                    case 6:
                        iv.setBackgroundColor(getResources().getColor(R.color.shapeDefault));
                        ivParams = new TableRow.LayoutParams(minWD, maxWD);
                        lines[i][j] = 1;
                        break;
                    case 8:
                        iv.setBackgroundColor(getResources().getColor(R.color.lineUnchecked));
                        iv.setOnClickListener(getLineOnClick(iv));
                        ivParams = new TableRow.LayoutParams(maxWD, minWD);
                        break;
                    case 9:
                        iv.setBackgroundColor(getResources().getColor(R.color.lineUnchecked));
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
                        turn = turn == 0 ? 1 : 0;
                    }
                    else addition = true;
                }
                else {
                    addition = false;
                    turn = turn == 0 ? 1 : 0;
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
                if (lines[i][j] == 1) continue;
                if (lines[i - 1][j] == 1 && lines[i][j - 1] == 1 && lines[i + 1][j] == 1 && lines[i][j + 1] == 1) {
                    ImageView iv = (ImageView) findViewById(3000 + i * 10 + j);
                    iv.setImageResource(turn == 0 ? R.drawable.cross : R.drawable.circle);
                    lines[i][j] = 1;
                    if (turn == 0) {
                        pOne++;
                        TextView tv = (TextView)findViewById(R.id.pOnePTV);
                        tv.setText(String.valueOf(pOne));
                    }
                    else {
                        pTwo++;
                        TextView tv = (TextView)findViewById(R.id.pTwoPTV);
                        tv.setText(String.valueOf(pTwo));
                    }
                    point = true;
                }
            }
        }
        return point;
    }
}