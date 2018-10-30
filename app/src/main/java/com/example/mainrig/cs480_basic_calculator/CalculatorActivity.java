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
    StringBuilder output = new StringBuilder("");

    //counter for number of open parentheses
    private int numOpenParentheses = 0;

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
                //if input is empty, do not append operator
                if(isEmpty(input)){
                    return;
                }
                //check to see if another operator was just added. if so, do nothing.
                else if(isNum(input.charAt(input.length() - 1))) {
                    input.append("+");
                    textViewInput.setText(input);
                }
                else
                    overwriteOp('+');
                textViewInput.setText(input);
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if input is empty, do not append operator
                if(isEmpty(input)){
                    return;
                }
                //check to see if another operator was just added. if so, do nothing.
                else if(isNum(input.charAt(input.length() - 1)))
                    input.append("-");

                else
                    overwriteOp('-');
                textViewInput.setText(input);
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if input is empty, do not append operator
                if(isEmpty(input)){
                    return;
                }
                //check to see if another operator was just added. if so, do nothing.
                else if(isNum(input.charAt(input.length() - 1)))
                    input.append("×");

                else
                    overwriteOp('×');
                textViewInput.setText(input);
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if input is empty, do not append operator
                if(isEmpty(input)){
                    return;
                }
                //check to see if another operator was just added. if so, do nothing.
                else if(isNum(input.charAt(input.length() - 1)))
                    input.append("÷");

                else
                    overwriteOp('÷');
                textViewInput.setText(input);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                input = new StringBuilder("");
                textViewInput.setText(input);
                numOpenParentheses = 0;
            }
        });
        buttonSign.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if input is empty, append negative sign change
                if(input.length() == 0){
                    input.append("(˗");
                    numOpenParentheses++;
                    textViewInput.setText(input);
                    return;
                }
                //search through input for the beginning of the current number
                for(int i = input.length() - 1; i >= 0; i--){
                    char current = input.charAt(i);
                    if(!isNum(current) && !(current == '.')){
                        //check if non-numeric character is part of a previously added sign change
                        if(current == '˗' && input.charAt(i - 1) == '('){
                                input.delete(i-1, i+1);
                                numOpenParentheses--;
                                textViewInput.setText(input);
                                break;
                        }
                        //if not, put sign change ahead of current index
                        input.insert(i+1, "(˗");
                        numOpenParentheses++;
                        textViewInput.setText(input);
                        break;
                    }
                    if(i == 0){
                        input.insert(0, "(˗");
                        numOpenParentheses++;
                        textViewInput.setText(input);
                        break;
                    }
                }
            }
        });
        buttonDecimal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if input is empty, append "0."
                if(isEmpty(input)){
                    input.append("0.");
                }
                //if current character is a number, check that current's number is not part of an
                //existing decimal point number.
                else if(isNum(input.charAt(input.length() - 1))){
                    for(int i = input.length() - 1; i >= 0; i--){
                        //if this is already an existing decimal point number, do nothing
                        if(input.charAt(i) == '.'){
                            break;
                        }
                        else if(!isNum(input.charAt(i)) || i == 0){
                            input.append('.');
                            break;
                        }
                    }
                }
                //if current character is a closing parentheses, append "×0." to input
                else if(input.charAt(input.length() - 1) == ')'){
                    input.append("×0.");
                }
                else{
                    input.append("0.");
                }
                textViewInput.setText(input);
            }
        });
        buttonExponent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if input is empty, do not append operator
                if(isEmpty(input)){
                    return;
                }
                //check to see if another operator was just added. if so, do nothing.
                else if(isNum(input.charAt(input.length() - 1))) {
                    input.append("^(");
                    numOpenParentheses++;
                    textViewInput.setText(input);
                    return;
                }
                else
                    return;
            }
        });
        buttonParentheses.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //if input is empty, or character at the end of input is an operator or
                // opening parentheses, append open parentheses
                if(isEmpty(input) || isOperator(input.charAt(input.length() - 1))
                        || input.charAt(input.length() - 1) == '('){
                    input.append("(");
                    numOpenParentheses++;
                    textViewInput.setText(input);
                }
                //if character at the end of input is a number, check to see if an opening
                //parentheses precedes that number.
                else{
                    if(numOpenParentheses != 0){
                        input.append(")");
                        numOpenParentheses--;
                        textViewInput.setText(input);
                    }
                    //should the loop finish without returning, append "×(" to input
                    else {
                        input.append("×(");
                        numOpenParentheses++;
                        textViewInput.setText(input);
                    }
                }
            }
        });


    }

    //method returns true if passed character is a number 1-9, false if something else
    public boolean isNum(char c){
        if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5'
                || c == '6' || c == '7' || c == '8' || c == '9')
            return true;
        else
            return false;
    }

    //method returns true if passed character is an operator, false if something else.
    public boolean isOperator(char c){
        if(c == '+' || c == '-' || c == '÷' || c == '×')
            return true;
        else
            return false;
    }

    //method returns true if input is empty, false if input is not empty
    public boolean isEmpty(StringBuilder sb){
        if(sb.length() == 0)
            return true;
        else
            return false;
    }

    //method overwrites operator if user tries to input an operator immediately after another.
    public void overwriteOp(char op){
        if(isOperator(input.charAt(input.length() - 1)))
            input.setCharAt(input.length() - 1, op);
    }


}
