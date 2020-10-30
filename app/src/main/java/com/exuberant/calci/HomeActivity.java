package com.exuberant.calci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private String currentNumber = "", totalCalculation = "";
    private TextView totalCalculationTextView, currentAnswerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_point).setOnClickListener(this);
        findViewById(R.id.btn_opening_brace).setOnClickListener(this);
        findViewById(R.id.btn_closing_brace).setOnClickListener(this);
        findViewById(R.id.btn_addition).setOnClickListener(this);
        findViewById(R.id.btn_subtraction).setOnClickListener(this);
        findViewById(R.id.btn_multiplication).setOnClickListener(this);
        findViewById(R.id.btn_division).setOnClickListener(this);
        findViewById(R.id.btn_equals).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        totalCalculationTextView = findViewById(R.id.tv_total_calculation);
        currentAnswerTextView = findViewById(R.id.tv_current_calculation);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        int id = button.getId();
        switch (id){
            //Handling all operators
            case R.id.btn_addition :
            case R.id.btn_division :
            case R.id.btn_multiplication :
            case R.id.btn_subtraction :
                handleOperatorClick(button.getText().toString());
                break;

            //Handling clear button
            case R.id.btn_clear:
                currentNumber = "";
                totalCalculation = "";
                break;

            //Handle calculation
            case R.id.btn_equals:
                totalCalculation += currentNumber;
                calculateAnswer();
                break;

            //Handle other numerical button clicks
            default:
                currentNumber += button.getText().toString();
        }
        updateDisplay();
    }

    private void updateDisplay(){
        totalCalculationTextView.setText(totalCalculation);
        currentAnswerTextView.setText(currentNumber);
    }

    private void handleOperatorClick(String operator){
        if (!(currentNumber.equals("") || currentNumber.length() == 0)) {
            totalCalculation += currentNumber + operator;
            currentNumber = "";
        } else {
            if(totalCalculation.length()>0){
                totalCalculation = totalCalculation.substring(0, totalCalculation.length() - 1);
            }
            totalCalculation += operator;
        }
    }

    private double add(double a, double b){
        return a + b;
    }

    private double sub(double a, double b){
        return  a + b;
    }

    private double mul(double a, double b){
        return  a + b;
    }

    private double div(double a, double b){
        return a + b;
    }

    private void calculateAnswer(){
        //Use totalCalculation string to get final answer and display it
        double answer = 0.0;
        updateDisplay();
    }
}
