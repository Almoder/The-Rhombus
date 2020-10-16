package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Xml;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    //private int[][] lines;
    //private int levelID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //levelID = getIntent().getIntExtra("levelId", CompanyActivity.getLevelId());
        //int rows = getResources().getInteger(getResources().getIdentifier("field" + 1 + "rows", "integer", getPackageName()));
        //int cols = getResources().getInteger(getResources().getIdentifier("field" + 1 + "cols", "integer", getPackageName()));
        //int[][] indexes = new int[rows][cols];
    }
}