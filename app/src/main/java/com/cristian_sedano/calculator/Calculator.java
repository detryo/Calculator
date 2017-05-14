package com.cristian_sedano.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    private EditText result, newNumber;
    private TextView displayOperation;
    private Double operand = null;
    private String pendingOperation = "=";
    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND = "Operand";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        result = (EditText) findViewById(R.id.Result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.displayOperation);
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        Button buttonEqual = (Button) findViewById(R.id.buttonEqual);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMulty = (Button) findViewById(R.id.buttonMulty);
        Button buttonSubstract = (Button) findViewById(R.id.buttonSubstract);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operand = 0.0;
                displayOperation.setText("");
                result.setText("");
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String s = b.getText().toString();
                newNumber.append(s);
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String operation = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    peroformOperation(doubleValue, operation);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperation = operation;
                displayOperation.setText(pendingOperation);
            }
        };
        buttonEqual.setOnClickListener(listener1);
        buttonDivide.setOnClickListener(listener1);
        buttonMulty.setOnClickListener(listener1);
        buttonSubstract.setOnClickListener(listener1);
        buttonAdd.setOnClickListener(listener1);
        Button buttonNeg = (Button) findViewById(R.id.buttonNeg);
        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentValue = newNumber.getText().toString();
                if (currentValue.length() == 0) {
                    newNumber.setText("-");
                } else {
                    try{
                        Double doubleValue = Double.valueOf(currentValue);
                        doubleValue *= 1;
                        newNumber.setText(doubleValue.toString());
                    }catch (NumberFormatException e){
                        newNumber.setText("");
                    }
                }
            }
        });
    }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            outState.putString(STATE_PENDING_OPERATION, pendingOperation);
            if (operand!=null){
                outState.putDouble(STATE_OPERAND, operand);
            }
            super.onSaveInstanceState(outState);
        }
        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
            operand = savedInstanceState.getDouble(STATE_OPERAND);
            displayOperation.setText(pendingOperation);
        }

    private void peroformOperation(Double value, String operation){
        if (null==operand) {
            operand = value;
        }else {
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "+":
                    operand += value;
                    break;
                case "-":
                    operand -= value;
                    break;
                case "X":
                    operand *= value;
                    break;
                case "/":
                    if (value==0){
                        operand = 0.0;
                    }else {
                        operand/=value;
                    }
                case "=":
                    operand = value;
                    break;
                default:
                    break;
            }
        }
        result.setText(operand.toString());
        newNumber.setText("");
    }
}
