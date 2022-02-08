package com.zacharyharrison.final_project.fragments;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zacharyharrison.final_project.R;
import com.zacharyharrison.final_project.data_processing.Combinations;
import com.zacharyharrison.final_project.data_processing.ExpressionEvaluator;
import com.zacharyharrison.final_project.data_processing.ExpressionToDiceConverter;
import com.zacharyharrison.final_project.models.Dice;
import com.zacharyharrison.final_project.viewmodels.EquationViewModel;

import java.text.DecimalFormat;
import java.util.Arrays;

public class GraphDisplayFragment extends Fragment {
    public GraphDisplayFragment() {
        super(R.layout.graph_display_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EquationViewModel viewModel = new ViewModelProvider(requireActivity()).get(EquationViewModel.class);

        Button closeButton = (Button) view.findViewById(R.id.close_graph_button);
        closeButton.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });

        try {
            Dice die = ExpressionToDiceConverter.expressionToDice(
                    viewModel.getCurrentEquation().getValue().expression);
            Combinations combinations = new Combinations(die.numOfDice, die.numOfSides, die.dropLow, die.dropHigh);

            DecimalFormat decimalFormat = new DecimalFormat("#####.##");
            TextView rollText = (TextView) view.findViewById(R.id.roll_display);
            rollText.setText("Roll: " + viewModel.getCurrentEquation().getValue().expression.replaceAll(",", ""));
            TextView averageText = (TextView) view.findViewById(R.id.average_display);
            double mean = Double.parseDouble(ExpressionEvaluator.solve(combinations.getMean() + die.bonus));
            averageText.setText("Average: " + decimalFormat.format(mean));
            TextView standDevText = (TextView) view.findViewById(R.id.standard_deviation_display);
            standDevText.setText("Standard Deviation: " + decimalFormat.format(combinations.getStandardDeviation()));

            int[] sums = combinations.getSums();
            double[] realSums = new double[sums.length];
            int[] realFuncDist = combinations.getFuncDist();
            double[] realProbDist = combinations.getProbDist();
            for (int i = 0; i < sums.length; i++) {
                realSums[i] = Double.parseDouble(ExpressionEvaluator.solve((double) sums[i] + die.bonus));
            }

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.distribution_recyclerView);
            recyclerView.setAdapter(new DistributionAdapter(realSums, realFuncDist, realProbDist));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }catch (Exception err) {
            viewModel.setCurrentAnswerToError();
        }

        // This code is commented out because I want to find a way to make it work eventually,
        // but I don't have time to do that for this assignment.
//        LinearLayout chanceRollLayout = view.findViewById(R.id.chance_to_roll_layout);
//
//        EditText numLow_EditText = new EditText(getContext());
//        numLow_EditText.setInputType(InputType.TYPE_CLASS_NUMBER);
//
//        TextView inbetweenTextView = new TextView(getContext());
//        inbetweenTextView.setText(getResources().getString(R.string.in_between_text));
//
//        EditText numHigh_EditText = new EditText(getContext());
//        numLow_EditText.setInputType(InputType.TYPE_CLASS_NUMBER);
//
//        Button equalsButton = new Button(getContext());
//        equalsButton.setText("=");
//
//        TextView percentChance = new TextView(getContext());
//        percentChance.setText("... %");
//
//        chanceRollLayout.addView(numLow_EditText);
//        chanceRollLayout.addView(inbetweenTextView);
//        chanceRollLayout.addView(numHigh_EditText);
//        chanceRollLayout.addView(equalsButton);
//        chanceRollLayout.addView(percentChance);
//
//        equalsButton.setOnClickListener(view1 -> {
//            int low = Integer.parseInt(String.valueOf(numLow_EditText.getText()));
//            int high = Integer.parseInt(String.valueOf(numHigh_EditText.getText()));
//            percentChance.setText(combinations.getProbabilityOf(low, high) + "%");
//        });



        // The following code is an attempt at a graph for the distribution.
        // However, the most promising library I found find was not super great.
        // I'm leaving the following code in case it is salvageable.
//
//        HorizontalBarChart barChart = (HorizontalBarChart) view.findViewById(R.id.graph_dice_distribution);
//        List<BarEntry> barProbList = new ArrayList<>();
//        double[] probDist = combinations.getProbDist();
//        for (int i = 0; i < probDist.length; i++) {
//            barProbList.add(new BarEntry((float) probDist[i], i));
//        }
//        BarDataSet barDataSet = new BarDataSet(barProbList, "Probability");
//        ArrayList<String> barSumList = new ArrayList<>();
//        for (int sum: combinations.getSums()) {
//            barSumList.add(String.valueOf(sum));
//        }
//        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barSumList));
//        BarData barData = new BarData(barDataSet);
//        barChart.setData(barData);
    }
}
