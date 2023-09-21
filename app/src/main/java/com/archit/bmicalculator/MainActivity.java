package com.archit.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupCalculateClickButton();
    }
    private void findViews(){
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setupCalculateClickButton() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult=calculateBmi();
                String age=ageEditText.getText().toString();
                int ageInt=Integer.parseInt(age);
                if(ageInt>=18){
                    displayResult(bmiResult);
                }
                else {
                    displayGuidance(bmiResult);
                }

            }
        });
    }

    private double calculateBmi() {

        String feet=feetEditText.getText().toString();
        String inches=inchesEditText.getText().toString();
        String weight=weightEditText.getText().toString();

        int feetInt=Integer.parseInt(feet);
        int inchesInt=Integer.parseInt(inches);
        int weightInt=Integer.parseInt(weight);

        int totalInches= (feetInt * 12) + inchesInt;
        double heightInMeters=totalInches * 0.0254;
        double bmi=weightInt/(heightInMeters * heightInMeters);
        return bmi;

    }
    private void displayResult(double bmiResult) {
        DecimalFormat df=new DecimalFormat("0.00");
        String bmiRoundOffResult=df.format(bmiResult);
        String finalResult;
        if(bmiResult<18.5){
            finalResult=bmiRoundOffResult+" - You are under weight.";
            resultText.setText(finalResult);

        } else if (bmiResult>25) {
            finalResult=bmiRoundOffResult+" - You are over weight.";
            resultText.setText(finalResult);
        }
        else{
            finalResult=bmiRoundOffResult+" - You are healthy weight.";
            resultText.setText(finalResult);
        }

    }
    private void displayGuidance(double bmiResult) {
        DecimalFormat a=new DecimalFormat("0.00");
        String bmiRoundOffResult=a.format(bmiResult);
        String finalResult;
        if(maleButton.isChecked()){
            finalResult=bmiRoundOffResult+ " - As you are under 18, please consult with your doctor for the healthy range for boys";
            resultText.setText(finalResult);

        } else if (femaleButton.isChecked()) {
            finalResult=bmiRoundOffResult+ " - As you are under 18, please consult with your doctor for the healthy range for girls";
            resultText.setText(finalResult);
        }
        else{
            finalResult=bmiRoundOffResult+ " - As you are under 18, please consult with your doctor for the healthy range";
            resultText.setText(finalResult);
        }
    }


    
}