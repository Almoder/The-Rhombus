package space.almoder.therhombus.shop;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import space.almoder.therhombus.R;
import space.almoder.therhombus.support.RhombusData;
import space.almoder.therhombus.support.ShopDialogFragment;

class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    ArrayList<Items_shop> list;
    Activity context;
    public ShopAdapter(ArrayList<Items_shop> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_shoping,parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        System.out.println("C");
        holder.nameOfProduct.setText(list.get(position).getName());
        holder.costOfProduct.setText(list.get(position).getCost());
        holder.icProduct.setImageDrawable(list.get(position).getIcProduct());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RhombusData.getCoinsCount(context)
                        .subscribe(getObserverForCoins(
                                Integer.parseInt(list.get(position).getCost()),position));
            }
        });
    }

    private Observer<Integer> getObserverForCoins(int Cost, int position){
        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onNext(Integer integer) {
                if (integer != null) {
                   if(Cost <= integer){
                       ShopDialogFragment d = new  ShopDialogFragment(Cost);
                       d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                           @Override
                           public void onDismiss(DialogInterface dialog) {
                               Intent intent = context.getIntent();
                               context.startActivity(intent);
                               context.finish();
                           }
                       });
                       d.show(((ShopActivity)context).getSupportFragmentManager(), list.get(position).getKey());
                   }
                   else {
                      Toast NoMoney =  Toast.makeText(context.getApplicationContext(),"You don't have enough souls", Toast.LENGTH_LONG);
                      NoMoney.show();
                   }
                }
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        return observer;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameOfProduct;
        TextView costOfProduct;
        ImageView icProduct;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfProduct = itemView.findViewById(R.id.nameOfProduct);
            costOfProduct = itemView.findViewById(R.id.CostOfProduct);
            icProduct = itemView.findViewById(R.id.ic_product);
            this.itemView = itemView;
        }
    }
}
