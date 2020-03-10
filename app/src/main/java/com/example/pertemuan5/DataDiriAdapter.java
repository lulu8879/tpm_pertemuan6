/*
 * Creator  : Sakti Wicaksono
 * Class    : DataDiri Adapter
 * Date     : 10-03-2020
 */

package com.example.pertemuan5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pertemuan5.database.DataDiri;

public class DataDiriAdapter extends RecyclerView.Adapter<DataDiriAdapter.ViewHolder> {

    private DataDiri[] dataDiri;
    private DataDiriListener dataDiriListener;
    private Context context;

    public DataDiri getDataDiri(int index) {
        return dataDiri[index];
    }

    public DataDiriAdapter(Context context, DataDiri[] dataDiri, DataDiriListener dataDiriListener) {
        this.context = context;
        this.dataDiri = dataDiri;
        this.dataDiriListener = dataDiriListener;
    }

    @NonNull
    @Override
    public DataDiriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataDiriAdapter.ViewHolder holder, final int position) {
        holder.tv_name.setText(getDataDiri(position).getName());
        holder.tv_address.setText(getDataDiri(position).getAddress());
        holder.tv_gender.setText(new char[]{getDataDiri(position).getGender()},0,1);
        holder.tv_id.setText(String.valueOf(getDataDiri(position).getId()));

        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_update = new Intent(context, UpdateActivity.class);
                intent_update.putExtra("name", getDataDiri(position).getName());
                intent_update.putExtra("address", getDataDiri(position).getAddress());
                intent_update.putExtra("gender", getDataDiri(position).getGender());
                intent_update.putExtra("id", getDataDiri(position).getId());
                context.startActivity(intent_update);
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataDiriListener.onButtonDelete(getDataDiri(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataDiri.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_address, tv_gender, tv_id;
        Button btn_update, btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.list_item_tv_name);
            tv_address = itemView.findViewById(R.id.list_item_tv_address);
            tv_gender = itemView.findViewById(R.id.list_item_tv_gender);
            tv_id = itemView.findViewById(R.id.list_item_tv_id);
            btn_update = itemView.findViewById(R.id.list_item_btn_update);
            btn_delete = itemView.findViewById(R.id.list_item_btn_delete);
        }
    }
}
