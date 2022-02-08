package com.zacharyharrison.final_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zacharyharrison.final_project.R;
import com.zacharyharrison.final_project.database.ButtonDatabase;
import com.zacharyharrison.final_project.databinding.FragmentCalculatorBinding;
import com.zacharyharrison.final_project.models.ButtonData;
import com.zacharyharrison.final_project.viewmodels.EquationViewModel;


public class CalculatorFragment extends Fragment {
    public CalculatorFragment() {
        super(R.layout.fragment_calculator);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EquationViewModel viewModel = new ViewModelProvider(getActivity()).get(EquationViewModel.class);

        LinearLayout layout = (LinearLayout) getView().findViewById(R.id.screen_layout);
        GridLayout gridLayout = new GridLayout(getContext());
        gridLayout.setRowCount(6);
        gridLayout.setColumnCount(5);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        gridLayout.setLayoutParams(params);
        ButtonDatabase database = new ButtonDatabase();
        for (ButtonData buttonData: database.getButtonData()) {
            CalcButton button;
            if (buttonData.type == ButtonData.ButtonType.EVALUATE) {
                button = new CalcButton(getActivity(), buttonData,
                        getResources().getColor(R.color.EVALUATE_Button_Color, null), view1 -> {
                            try {
                                if (viewModel.currentExpressionHasDice()){
                                    viewModel.rollCurrentEquation();
                                }else{
                                    viewModel.solveCurrentEquation();
                                }
                            } catch (Exception err) {
                                viewModel.setCurrentAnswerToError();
                            }
                        });
            } else if (buttonData.type == ButtonData.ButtonType.CLEAR) {
                button = new CalcButton(getActivity(), buttonData,
                        getResources().getColor(R.color.CLEAR_Button_Color, null), view1 -> {
                            viewModel.clearCurrentExpression();
                        });
            } else if (buttonData.type == ButtonData.ButtonType.NUMBER) {
                button = new CalcButton(getActivity(), buttonData,
                        getResources().getColor(R.color.NUMBER_Button_Color, null),
                        view1 -> {
                            viewModel.appendCurrentExpression(buttonData.text);
                        });
            } else if (buttonData.type == ButtonData.ButtonType.OPERATOR){
                button = new CalcButton(getActivity(), buttonData,
                        getResources().getColor(R.color.OPERATOR_Button_Color, null),
                        view1  -> {
                            viewModel.appendCurrentExpression("," + buttonData.text + ",");
                        });
            } else if (buttonData.type == ButtonData.ButtonType.DIE) {
                button = new CalcButton(getActivity(), buttonData,
                        getResources().getColor(R.color.DICE_Button_Color, null),
                        view1  -> {
                            if (buttonData.text.equals("XdY")) {
                                viewModel.appendCurrentExpression(",d");
                            }else {
                                viewModel.appendCurrentExpression("," + buttonData.text + ",");
                            }
                        });

            } else if (buttonData.type == ButtonData.ButtonType.GRAPH) {
                button = new CalcButton(getActivity(), buttonData,
                        getResources().getColor(R.color.GRAPH_Button_Color, null),
                        view1  -> {
                            try {
                                if (viewModel.currentEquationIsGraphable()) {
                                    getParentFragmentManager().beginTransaction()
                                            .add(R.id.fragment_container_view, GraphDisplayFragment.class, null)
                                            .addToBackStack("display_results")
                                            .setReorderingAllowed(true)
                                            .commit();
                                    viewModel.graphingCurrentEquation();
                                }
                            } catch (Exception err) {
                                viewModel.setCurrentAnswerToError();
                            }
                        });

            } else { // buttonData.type == ButtonData.ButtonType.ERASE
                button = new CalcButton(getActivity(), buttonData,
                        getResources().getColor(R.color.ERASE_Button_Color, null),
                        view1  -> {
                            viewModel.eraseAllData();
                        });
            }
            gridLayout.addView(button);
        }
        layout.addView(gridLayout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        EquationViewModel viewModel = new ViewModelProvider(requireActivity()).get(EquationViewModel.class);
        FragmentCalculatorBinding binding = FragmentCalculatorBinding.inflate(inflater, container, false);

        viewModel.loadEquations();
        // when the app launches, it should scroll to the bottom of the recycler view
        int bottom = viewModel.getEquations().size() - 1;
        if (bottom >= 0) {
            binding.equationDisplayRecyclerView.scrollToPosition(bottom);
        }
        binding.equationDisplayRecyclerView.setAdapter(new EquationScreenAdapter(viewModel, equation -> {
            // this method gets called whenever the user clicks on an expression/answer on the screen
            viewModel.appendCurrentExpression(equation.expression);
            int bot = binding.equationDisplayRecyclerView.getAdapter().getItemCount()-1;
            binding.equationDisplayRecyclerView.scrollToPosition(bot);
        }));
        binding.equationDisplayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.getRoot().setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
        return binding.getRoot();
    }
}
