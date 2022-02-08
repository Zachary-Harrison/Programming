package com.zacharyharrison.final_project.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zacharyharrison.final_project.R;

import java.text.DecimalFormat;

public class DistributionAdapter extends RecyclerView.Adapter<DistributionAdapter.ViewHolder> {
    private final double[] sums;
    private final int[] funcDist;
    private final double[] probDist;

    public DistributionAdapter(double[] sums, int[] funcDist, double[] probDist){
        this.sums = sums;
        this.funcDist = funcDist;
        this.probDist = probDist;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.distribution_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView sum_item = holder.itemView.findViewById(R.id.sum_text);
        TextView funcText = holder.itemView.findViewById(R.id.func_text);
        TextView prob_item = holder.itemView.findViewById(R.id.prob_text);

        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        sum_item.setText(sums[position] + ":   ");
        funcText.setText(funcDist[position] + "   ");
        prob_item.setText("(" + (decimalFormat.format(100*probDist[position])) + "%)");
    }

    @Override
    public int getItemCount() {
        return sums.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
