package space.almoder.therhombus;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class FieldBuilder {
    private int[][] lines, indexes;
    private int[] colors;
    private int minWD, maxWD, over = 0;
    private ResourceManager rm;

    FieldBuilder(ResourceManager rm, TableLayout tl) {
        this.rm = rm;
        indexes = rm.getFieldRes();
        lines = new int[indexes.length][indexes[0].length];
        colors = rm.getFieldColors();
        minWD = rm.getMinWD();
        maxWD = rm.getMaxWD();
        buildField(tl);
    }

    FieldBuilder(ResourceManager rm, TableLayout tl, int[][] lines) {
        this.rm = rm;
        indexes = rm.getFieldRes();
        this.lines = lines;
        colors = rm.getFieldColors();
        minWD = rm.getMinWD();
        maxWD = rm.getMaxWD();
        buildField(tl);
    }

    private void buildField(TableLayout tl) {
        for (int i = 0; i < indexes.length; i++) {
            TableRow tr = new TableRow(rm.getContext());
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            tr.setLayoutParams(params);
            for (int j = 0; j < indexes[i].length; j++) {
                tr.addView(makeImageView(i, j));
            }
            tl.addView(tr);
        }
    }

    public void buildButtonField(TableLayout buttonField, View.OnClickListener ocl) {
        for (int i = 1; i < lines.length - 1; i++) {
            TableRow tr = new TableRow(rm.getContext());
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            tr.setLayoutParams(params);
            for (int j = 1; j < lines[i].length - 1; j++) {
                ImageView iv = new ImageView(rm.getContext());
                iv.setId(3300 + i * indexes[0].length + j);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setImageResource(R.drawable.empty);
                TableRow.LayoutParams ivParams = new TableRow.LayoutParams((minWD + maxWD) / 2, (minWD + maxWD) / 2);
                iv.setLayoutParams(ivParams);
                if (indexes[i][j] == 8 || indexes[i][j] == 9) {
                    iv.setOnClickListener(ocl);
                    if (lines[i][j] > 0) iv.setEnabled(false);
                }
                tr.addView(iv);
            }
            buttonField.addView(tr);
        }
    }

    public int[][] getLines() {
        return lines;
    }

    public int getOverHere() {
        return over;
    }

    private ImageView makeImageView(int i, int j) {
        ImageView iv = new ImageView(rm.getContext());
        iv.setId(3000 + i * indexes[0].length + j);
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
                iv.setBackgroundColor(colors[0]);
                ivParams = new TableRow.LayoutParams(minWD, minWD);
                lines[i][j] = 1;
                break;
            case 5:
                iv.setBackgroundColor(colors[1]);
                ivParams = new TableRow.LayoutParams(maxWD, minWD);
                lines[i][j] = 1;
                break;
            case 6:
                iv.setBackgroundColor(colors[2]);
                ivParams = new TableRow.LayoutParams(minWD, maxWD);
                lines[i][j] = 1;
                break;
            case 7:
                if (lines[i][j] > 0) iv.setImageResource(lines[i][j] == 1 ? R.drawable.cross : R.drawable.circle);
                else iv.setBackgroundColor(colors[3]);
                ivParams = new TableRow.LayoutParams(maxWD, maxWD);
                break;
            case 8:
                iv.setBackgroundColor(lines[i][j] == 1 ? rm.getP1Color() : lines[i][j] == 2 ? rm.getP2Color() : colors[4]);
                ivParams = new TableRow.LayoutParams(maxWD, minWD);
                if (lines[i][j] == 0) over++;
                break;
            case 9:
                iv.setBackgroundColor(lines[i][j] == 1 ? rm.getP1Color() : lines[i][j] == 2 ? rm.getP2Color() : colors[5]);
                ivParams = new TableRow.LayoutParams(minWD, maxWD);
                if (lines[i][j] == 0) over++;
                break;
            default:
                ivParams = new TableRow.LayoutParams(minWD, minWD);
                break;
        }
        iv.setLayoutParams(ivParams);
        return iv;
    }


}
