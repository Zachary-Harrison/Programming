package com.zacharyharrison.final_project.fragments;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.zacharyharrison.final_project.R;
import com.zacharyharrison.final_project.models.Equation;
import com.zacharyharrison.final_project.viewmodels.EquationViewModel;


public class EquationScreenAdapter extends RecyclerView.Adapter<EquationScreenAdapter.ViewHolder> {
    private ObservableArrayList<Equation> equations;
    private EquationViewModel viewModel;
    private OnExpressionClicked listener;

    public interface OnExpressionClicked {
        void onClick(Equation equation);
    }

    public EquationScreenAdapter(EquationViewModel viewModel, OnExpressionClicked listener) {
        this.viewModel = viewModel;
        this.listener = listener;

        this.equations = viewModel.getEquations();
        this.equations.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Equation>>() {
            @Override
            public void onChanged(ObservableList<Equation> sender) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<Equation> sender, int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<Equation> sender, int positionStart, int itemCount) {
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<Equation> sender, int fromPosition, int toPosition, int itemCount) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<Equation> sender, int positionStart, int itemCount) {
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        });
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equation_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView expressionTextView = holder.itemView.findViewById(R.id.expression_text);
        if (equations.get(position).expression != null) {
            expressionTextView.setText(equations.get(position).expression.replaceAll(",", ""));
            expressionTextView.setTextColor(holder.itemView.getResources().getColor(R.color.TextColor, null));
        }
        TextView answerTextView = holder.itemView.findViewById(R.id.answer_text);
        answerTextView.setText(equations.get(position).answer);
        answerTextView.setTextColor(holder.itemView.getResources().getColor(R.color.TextColor, null));

        holder.itemView.setOnClickListener(view -> {
            listener.onClick(equations.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return equations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}