package com.test.sample.customcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String strOperators = "+-*/";
    String strCurrentResult = null;
    String strCurrentOperator = null;
    String strPreviousOperator = null;
    boolean boolOperatorClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClickSetValues(View view){
        TextView operandHistory = (TextView)findViewById(R.id.txtOperand1);
        TextView currentOperand = (TextView)findViewById(R.id.txtOperand2);
        if(view instanceof Button){
            Button btnClicked = (Button) view;
            String valueClicked = String.valueOf(btnClicked.getText());
            if(strOperators.contains(valueClicked)){
                strPreviousOperator = strCurrentOperator;
                boolOperatorClicked = true;
                strCurrentResult = (strCurrentResult == null? currentOperand.getText().toString():performOperation(strCurrentResult, currentOperand.getText().toString(), strPreviousOperator.charAt(0)));
                operandHistory.setText(operandHistory.getText()!=null?operandHistory.getText().toString().concat(" ").concat(currentOperand.getText().toString()).concat(" ".concat(valueClicked)):currentOperand.getText().toString().concat("".concat(valueClicked)));
                currentOperand.setText(strCurrentResult);
                strCurrentOperator = valueClicked;
            }else if(valueClicked.equals("C")){
                currentOperand.setText(null);
                operandHistory.setText(null);
                strPreviousOperator = null;
                strCurrentOperator = null;
                strCurrentResult = null;
                boolOperatorClicked = false;
            }else{
                if(boolOperatorClicked) {
                    currentOperand.setText(null);
                    boolOperatorClicked = false;
                }
                currentOperand.setText(currentOperand.getText()!=null?currentOperand.getText().toString().concat(valueClicked):valueClicked);
            }
        }
    }

    public String performOperation(String operand1, String operand2, char operator){
        String result = null;
        switch (operator){
            case '+':
                result = String.valueOf(Integer.parseInt(operand1) + Integer.parseInt(operand2));
                break;
            case '-':
                result = String.valueOf(Integer.parseInt(operand1) - Integer.parseInt(operand2));
                break;
            case '*':
                result = String.valueOf(Integer.parseInt(operand1) * Integer.parseInt(operand2));
                break;
            case '/':
                if(Integer.parseInt(operand2)>0)
                    result = String.valueOf(Integer.parseInt(operand1) / Integer.parseInt(operand2));
                break;
        }
        return result;
    }

}
