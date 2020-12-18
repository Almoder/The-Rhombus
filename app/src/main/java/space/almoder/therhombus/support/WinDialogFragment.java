package space.almoder.therhombus.support;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import space.almoder.therhombus.R;


public class WinDialogFragment extends AppCompatDialogFragment implements View.OnClickListener {


    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RhombusData.addCoin(5,getContext());
        View v = inflater.inflate(R.layout.win_dialog, null);
        v.findViewById(R.id.winButton).setOnClickListener(this);
        if(RhombusData.getPreferenceManager(getContext()).getInt("theme", R.style.Game) == R.style.DarkTheme1) {
            System.out.println("Rrrrrrr");
            v.findViewById(R.id.layout).setBackgroundColor(getResources().getColor(R.color.gray_700));
        }
        return v;

    }

    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.winButton:
                getDialog().dismiss();
        }
    }


    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }
}
