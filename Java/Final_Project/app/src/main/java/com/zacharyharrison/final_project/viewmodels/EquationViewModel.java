package com.zacharyharrison.final_project.viewmodels;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.zacharyharrison.final_project.data_processing.DiceRoller;
import com.zacharyharrison.final_project.data_processing.ExpressionEvaluator;
import com.zacharyharrison.final_project.database.EquationsDatabase;
import com.zacharyharrison.final_project.models.Equation;

import java.util.List;

public class EquationViewModel extends AndroidViewModel {
    private EquationsDatabase database;
    private Handler handler;
    private ObservableArrayList<Equation> equations = new ObservableArrayList<>();
    private MutableLiveData<Equation> currentEquation = new MutableLiveData<>();
    private boolean loaded = false;


    public EquationViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, EquationsDatabase.class, "Equation_db").build();
        handler = new Handler();

//        new Thread(() -> {
//            ArrayList<Equation> equationEntries = (ArrayList<Equation>) database.getEquationsDao().getAll();
//            equations.addAll(equationEntries);
//        }).start();
    }

    public void loadEquations() {
        if (!loaded) {
            loaded = true;
            new Thread(() -> {
                List<Equation> db_equations = database.getEquationsDao().getAll();
                handler.post(() -> {
                    equations.addAll(db_equations);
                    if (db_equations.size() > 0){
                        currentEquation.setValue(equations.get(db_equations.size() - 1));
                        solveCurrentEquation();
                    }else{
                        createNewEquation();
                    }
                });
            }).start();
        }
    }

    public ObservableArrayList<Equation> getEquations() {
        return equations;
    }

    public MutableLiveData<Equation> getCurrentEquation() {
        return currentEquation;
    }

    private void createNewEquation() {
        new Thread(() -> {
            Equation newEquation = new Equation();
            newEquation.id = database.getEquationsDao().insert(newEquation);
            newEquation.expression = "";
            handler.post(() -> {
                setCurrentEquation(newEquation);
                equations.add(currentEquation.getValue());
            });
        }).start();
    }

    private void updateCurrentEquation() {
        if (currentEquation.getValue() != null) {
            new Thread(() -> {
                database.getEquationsDao().update(currentEquation.getValue());
                int index = equations.indexOf(currentEquation.getValue());
                handler.post(() -> {
                    equations.set(index, currentEquation.getValue());
                });
            }).start();
        }
    }

    /*
     CalculatorFragment uses all of the following methods in order to manipulate data:
    */

    public boolean currentExpressionUpdatable() {
        if (currentEquation.getValue() != null) {
            if (currentEquation.getValue().expression != null) {
                return !currentEquation.getValue().expression.equals("");
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public boolean currentExpressionHasDice() {
        if (currentEquation.getValue() != null) {
            return (currentEquation.getValue().expression.contains("d"));
        }else {
            return false;
        }
    }

    public void setCurrentEquation(Equation equation) {
        currentEquation.setValue(equation);
        updateCurrentEquation();
    }

    public void appendCurrentExpression(String appendExpression) {
        if (currentEquation.getValue() != null) {
            currentEquation.getValue().expression += appendExpression;
            updateCurrentEquation();
        }
    }

    public void clearCurrentExpression() {
        if (currentEquation.getValue() != null) {
            currentEquation.getValue().expression = "";
            updateCurrentEquation();
        }
    }

    public void setCurrentAnswerToError() {
        if (currentEquation.getValue() != null) {
            currentEquation.getValue().answer = "Error";
            updateCurrentEquation();
        }
    }

    public void eraseAllData() {
        new Thread(() -> {
            database.clearAllTables();
            handler.post(() -> {
                equations.clear();
            });
            createNewEquation();
        }).start();
    }

    public void solveCurrentEquation() {
        new Thread(() -> {
            if (currentExpressionUpdatable()) {
                handler.post(() -> {
                    try {
                        currentEquation.getValue().answer = ExpressionEvaluator.solve(currentEquation.getValue().expression);
                    }catch (Exception err) {
                        currentEquation.getValue().answer = "Error";
                    }

                    updateCurrentEquation();
                    createNewEquation();
                });
            }
        }).start();
    }

    public void rollCurrentEquation() {
        new Thread(() -> {
            if (currentExpressionUpdatable()) {
                handler.post(() -> {
                    try {
                        DiceRoller roller = new DiceRoller(currentEquation.getValue().expression);
                        currentEquation.getValue().answer = String.valueOf(roller.roll());
                    }catch (Exception err) {
                        currentEquation.getValue().answer = "Error";
                    }
                    updateCurrentEquation();
                    createNewEquation();
                });
            }
        }).start();
    }

    public boolean currentEquationIsGraphable() {
        if (currentEquation.getValue() != null) {
            return currentEquation.getValue().expression.contains("d");
        }else {
            return false;
        }
    }

    public void graphingCurrentEquation() {
        if (currentEquation.getValue() != null) {
            currentEquation.getValue().answer = "GRAPHED!";
            handler.post(() -> {
                updateCurrentEquation();
                createNewEquation();
            });
        }
    }
}
