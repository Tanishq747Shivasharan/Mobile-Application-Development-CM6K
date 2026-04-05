package com.example.examinationprep;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnAdd, btnSub, btnPro, btnDiv;
    Button btnEquals, btnClear, btnDecimal, btnBackspace;

    double firstNum = 0;
    double secondNum = 0;
    String currentOperation = "";
    boolean isNewNumber = true;
    boolean hasDecimal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        tvDisplay = findViewById(R.id.tvDisplay);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSubtract);
        btnPro = findViewById(R.id.btnMultiply);
        btnDiv = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnClear = findViewById(R.id.btnClear);
        btnDecimal = findViewById(R.id.btnDecimal);
        btnBackspace = findViewById(R.id.btnBackspace);
    }

    private void setupClickListeners() {
        // Numbers 0-9
        btn0.setOnClickListener(v -> onNumberClick("0"));
        btn1.setOnClickListener(v -> onNumberClick("1"));
        btn2.setOnClickListener(v -> onNumberClick("2"));
        btn3.setOnClickListener(v -> onNumberClick("3"));
        btn4.setOnClickListener(v -> onNumberClick("4"));
        btn5.setOnClickListener(v -> onNumberClick("5"));
        btn6.setOnClickListener(v -> onNumberClick("6"));
        btn7.setOnClickListener(v -> onNumberClick("7"));
        btn8.setOnClickListener(v -> onNumberClick("8"));
        btn9.setOnClickListener(v -> onNumberClick("9"));

        // Operations
        btnAdd.setOnClickListener(v -> onOperationClick("+"));
        btnSub.setOnClickListener(v -> onOperationClick("-"));
        btnPro.setOnClickListener(v -> onOperationClick("*"));
        btnDiv.setOnClickListener(v -> onOperationClick("/"));

        // Controls
        btnEquals.setOnClickListener(v -> onEqualsClick());
        btnClear.setOnClickListener(v -> onClearClick());
        btnDecimal.setOnClickListener(v -> onDecimalClick());
        btnBackspace.setOnClickListener(v -> onBackspaceClick());
    }

    private void onNumberClick(String number) {
        String currentText = tvDisplay.getText().toString();

        if (isNewNumber) {
            tvDisplay.setText(number);
            isNewNumber = false;
            hasDecimal = false;
        } else {
            if (currentText.equals("0") && !number.equals("0")) {
                tvDisplay.setText(number);
            } else if (!(currentText.equals("0") && number.equals("0"))) {
                String newText = currentText + number;
                tvDisplay.setText(newText);
            }
        }
    }

    private void onOperationClick(String operation) {
        String currentText = tvDisplay.getText().toString();
        firstNum = Double.parseDouble(currentText);
        currentOperation = operation;
        isNewNumber = true;
        hasDecimal = false;
    }

    private void onEqualsClick() {
        String currentText = tvDisplay.getText().toString();
        secondNum = Double.parseDouble(currentText);

        double result = 0;
        boolean hasError = false;

        switch (currentOperation) {
            case "+":
                result = firstNum + secondNum;
                break;
            case "-":
                result = firstNum - secondNum;
                break;
            case "*":
                result = firstNum * secondNum;
                break;
            case "/":
                if (secondNum == 0) {
                    hasError = true;
                } else {
                    result = firstNum / secondNum;
                }
                break;
            default:
                result = secondNum;
                break;
        }

        if (hasError) {
            tvDisplay.setText("Error");
        } else {
            if (result == (long) result) {
                tvDisplay.setText(String.valueOf((long) result));
            } else {
                tvDisplay.setText(String.valueOf(result));
            }
        }

        isNewNumber = true;
        hasDecimal = false;
        currentOperation = "";
    }

    private void onClearClick() {
        firstNum = 0;
        secondNum = 0;
        currentOperation = "";
        isNewNumber = true;
        hasDecimal = false;
        tvDisplay.setText("0");
    }

    private void onDecimalClick() {
        if (isNewNumber) {
            tvDisplay.setText("0.");
            isNewNumber = false;
            hasDecimal = true;
        } else if (!hasDecimal) {
            String currentText = tvDisplay.getText().toString();
            tvDisplay.setText(currentText + ".");
            hasDecimal = true;
        }
    }

    private void onBackspaceClick() {
        String currentText = tvDisplay.getText().toString();

        if (currentText.length() > 1) {
            String newText = currentText.substring(0, currentText.length() - 1);
            tvDisplay.setText(newText);
            if (!newText.contains(".")) {
                hasDecimal = false;
            }
        } else {
            tvDisplay.setText("0");
            isNewNumber = true;
            hasDecimal = false;
        }
    }
}