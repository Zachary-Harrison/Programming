package com.zacharyharrison.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zacharyharrison.final_project.fragments.CalculatorFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, CalculatorFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }
    }
}