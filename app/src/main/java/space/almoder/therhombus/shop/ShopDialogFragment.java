package space.almoder.therhombus.shop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import space.almoder.therhombus.R;
import space.almoder.therhombus.support.RhombusData;

public class ShopDialogFragment extends AppCompatDialogFragment {
    int cost;
    private DialogInterface.OnDismissListener onDismissListener;

    public ShopDialogFragment(int cost) {
        this.cost = cost;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle(R.string.buy_theme)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(R.string.buy_mes)
                .setPositiveButton("OK", (dialog, which) -> {
                    RhombusData.writeOffCoins(cost,getContext());
                    RhombusData.markThisItemAsPurchased(getTag(),getContext());
                })
                .setNegativeButton("Отмена", null)
                .create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

}