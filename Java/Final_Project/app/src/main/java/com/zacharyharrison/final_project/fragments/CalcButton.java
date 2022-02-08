package com.zacharyharrison.final_project.fragments;

import android.content.Context;
import android.view.Gravity;
import android.widget.GridLayout;
import androidx.appcompat.widget.AppCompatButton;

import com.zacharyharrison.final_project.R;
import com.zacharyharrison.final_project.models.ButtonData;

public class CalcButton extends AppCompatButton {

    public CalcButton(Context context, ButtonData buttonData, int Color, OnClickListener onClickListener) {
        super(context);
        setBackgroundColor(Color);
        setText(buttonData.text);
        if (buttonData.type == ButtonData.ButtonType.ERASE){
            setTextColor(getResources().getColor(R.color.WARNING_TEXT_COLOR, null));
        }else{
            setTextColor(getResources().getColor(R.color.TextColor, null));
        }
        setTextSize(20);
        setTransformationMethod(null);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setOnClickListener(onClickListener);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(buttonData.row, 1, 1);
        params.columnSpec = GridLayout.spec(buttonData.col, buttonData.colSpan, 1);
        // God only knows why I need this line of code in order for the buttons to be spaced properly
        params.width = 0;

        params.setMargins(1, 1, 1, 1);

        setLayoutParams(params);
    }
}
