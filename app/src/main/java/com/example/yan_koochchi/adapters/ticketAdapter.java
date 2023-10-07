package com.example.yan_koochchi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yan_koochchi.R;
import com.example.yan_koochchi.models.bookingModel;

import java.util.ArrayList;

public class ticketAdapter extends RecyclerView.Adapter<ticketAdapter.viewHolder> {

    ArrayList<bookingModel> list;
    Context context;
    bookingModel model;
    int user_id;
    public ticketAdapter(ArrayList<bookingModel> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false);
        return new viewHolder(view);
    }

    public  void onBindViewHolder(@NonNull viewHolder holder,int position)
    {
        model=list.get(position);
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.txt_id.setText(model.getId());
        holder.txt_from_to.setText(model.getFrom() + " - "+model.getTo());
        holder.txt_time.setText((model.getDate()+""));
        holder.txt_count.setText(model.getCount()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {


        TextView txt_from_to,txt_count,txt_id,txt_time;
        ImageView cancel;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txt_from_to=itemView.findViewById(R.id.txt_from_to);
            txt_count=itemView.findViewById(R.id.txt_count);
            txt_id=itemView.findViewById(R.id.txt_id);
            txt_time=itemView.findViewById(R.id.txt_time);
            cancel=itemView.findViewById(R.id.cancel);
        }
    }


}
