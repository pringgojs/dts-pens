package com.example.retrofitapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitapi.AddAndUpdateActivity;
import com.example.retrofitapi.MainActivity;
import com.example.retrofitapi.R;
import com.example.retrofitapi.models.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private final ArrayList<Item> listItem = new ArrayList<Item>();
    private Context context;

    public ItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.tvName.setText(listItem.get(position).getNama());
        holder.tvBrand.setText(listItem.get(position).getBrand());
        holder.tvPrice.setText("Rp. " + listItem.get(position).getPrice());

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Name : " + listItem.get(position).getNama(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, AddAndUpdateActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("item",listItem.get(position));
                context.startActivity(intent);
            }
        });

        holder.cvItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void setListItem(ArrayList<Item> listItem) {

        if (listItem.size() > 0) {
            this.listItem.clear();

        }

        this.listItem.addAll(listItem);
        notifyDataSetChanged();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvBrand;
        CardView cvItem;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvBrand = itemView.findViewById(R.id.tvBrand);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            cvItem = itemView.findViewById(R.id.cvItem);
        }
    }
}
