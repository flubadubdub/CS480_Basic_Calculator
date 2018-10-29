package com.example.mainrig.cs480_basic_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Scanner;
import java.util.Stack;
import java.lang.StringBuilder;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    //when the user pressed a button, the button's corresponding action is added to
    //the string, to be parsed and then calculated as a mathematical expression later.
    StringBuilder input = new StringBuilder("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //create text view for input
        final TextView textViewInput = findViewById(R.id.txtInput);
        textViewInput.setText(input);

        //create all button objects
        final Button buttonZero = findViewById(R.id.btnZero);
        final Button buttonOne = findViewById(R.id.btnOne);
        final Button buttonTwo = findViewById(R.id.btnTwo);
        final Button buttonThree = findViewById(R.id.btnThree);
        final Button buttonFour = findViewById(R.id.btnFour);
        final Button buttonFive = findViewById(R.id.btnFive);
        final Button buttonSix = findViewById(R.id.btnSix);
        final Button buttonSeven = findViewById(R.id.btnSeven);
        final Button buttonEight = findViewById(R.id.btnEight);
        final Button buttonNine = findViewById(R.id.btnNine);
        final Button buttonSubtract = findViewById(R.id.btnSubtract);
        final Button buttonAdd = findViewById(R.id.btnAdd);
        final Button buttonMultiply = findViewById(R.id.btnMultiply);
        final Button buttonExponent = findViewById(R.id.btnExponent);
        final Button buttonClear = findViewById(R.id.btnClear);
        final Button buttonParentheses = findViewById(R.id.btnParentheses);
        final Button buttonSign = findViewById(R.id.btnSign);
        final Button buttonEquals = findViewById(R.id.btnEquals);
        final Button buttonDivide = findViewById(R.id.btnDivide);
        final Button buttonDecimal = findViewById(R.id.btnDecimal);



        //create basic button functionality
        buttonZero.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("0");
                textViewInput.setText(input);
            }
        });
        buttonOne.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("1");
                textViewInput.setText(input);
            }
        });
        buttonTwo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("2");
                textViewInput.setText(input);
            }
        });
        buttonThree.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("3");
                textViewInput.setText(input);
            }
        });
        buttonFour.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("4");
                textViewInput.setText(input);
            }
        });
        buttonFive.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("5");
                textViewInput.setText(input);
            }
        });
        buttonSix.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("6");
                textViewInput.setText(input);
            }
        });
        buttonSeven.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("7");
                textViewInput.setText(input);
            }
        });
        buttonEight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("8");
                textViewInput.setText(input);
            }
        });
        buttonNine.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("9");
                textViewInput.setText(input);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("+");
                textViewInput.setText(input);
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("-");
                textViewInput.setText(input);
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("×");
                textViewInput.setText(input);
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input.append("÷");
                textViewInput.setText(input);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input = new StringBuilder("");
                textViewInput.setText(input);
            }
        });
        buttonSign.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if input is empty, append negative sign change
                if(input.length() == 0){
                    input.append("(-");
                    textViewInput.setText(input);
                    return;
                }
                //search through input for the beginning of the current number
                for(int i = input.length() - 1; i >= 0; i--){
                    char current = input.charAt(i);
                    if(!isNum(current) && !(current == '.')){
                        //check if non-numeric character is part of a previously added sign change
                        if(current == '-' && input.charAt(i - 1) == '('){
                                input.delete(i-1, i+1);
                                textViewInput.setText(input);
                                break;
                        }
                        //if not, put sign change ahead of current index
                        input.insert(i+1, "(-");
                        textViewInput.setText(input);
                        break;
                    }
                    if(i == 0){
                        input.insert(0, "(-");
                        textViewInput.setText(input);
                        break;
                    }
                }
            }
        });
        buttonDecimal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if input is empty, append "0."
                if(input.length() == 0){
                    input.append("0.");
                    textViewInput.setText(input);
                    return;
                }
                //if current character is a number, check that current's number is not part of an
                //existing decimal point number.
                if(isNum(input.charAt(input.length() - 1))){
                    for(int i = input.length() - 1; i >= 0; i--){
                        //if this is already an existing decimal point number, do nothing
                        if(input.charAt(i) == '.'){
                            return;
                        }
                        else if(!isNum(input.charAt(i)) || i == 0){
                            input.append('.');
                            textViewInput.setText(input);
                            return;
                        }
                    }
                }
                else{
                    input.append("0.");
                    textViewInput.setText(input);
                }
            }
        });



    }

    //method which checks if passed character is a number 1-9.
    public boolean isNum(char c){
        if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5'
                || c == '6' || c == '7' || c == '8' || c == '9') {
            //Toast.makeText(this, "is a number", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            //Toast.makeText(this, "is NO number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
