package space.almoder.therhombus.Shop;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import space.almoder.therhombus.R;

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
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameOfProduct;
        TextView costOfProduct;
        ImageView icProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfProduct = itemView.findViewById(R.id.nameOfProduct);
            costOfProduct = itemView.findViewById(R.id.CostOfProduct);
            icProduct = itemView.findViewById(R.id.ic_product);
        }

    }
}
