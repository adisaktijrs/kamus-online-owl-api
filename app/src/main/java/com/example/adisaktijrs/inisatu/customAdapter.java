package com.example.adisaktijrs.inisatu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by adisaktijrs on 1/14/2019.
 */

public class customAdapter extends RecyclerView.Adapter<customAdapter.ViewHolder>{

    private Context context;
    private List<dataModel> model;

    public customAdapter(Context context, List<dataModel> model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template,parent, false);
        ViewHolder vw = new ViewHolder(v);
        return vw;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        dataModel dm = model.get(position);
        holder.a1.setText(dm.getTy().toString());
        holder.a2.setText(dm.getDe().toString());
        holder.a3.setText(dm.getEx().toString());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView a1, a2, a3;
        public ViewHolder(View itemView) {
            super(itemView);

            a1 = (TextView) itemView.findViewById(R.id.tipe);
            a2 = (TextView) itemView.findViewById(R.id.definition);
            a3 = (TextView) itemView.findViewById(R.id.example);

        }
    }

}

