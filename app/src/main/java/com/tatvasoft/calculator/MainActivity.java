package com.tatvasoft.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnDot, btnZero, btnDivide, btnEquals, btnPlus, btnThree, btnTwo, btnOne, btnFour, btnFive, btnSix, btnMinus, btnMul, btnNine, btnSeven, btnEight, btnSingleClear, btnModule, btnIn, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
    }

    private void initControls() {
        initListeners();
    }

    private void initListeners() {
        btnDot = findViewById(R.id.btnDot);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMul = findViewById(R.id.btnMul);

        btnZero = findViewById(R.id.btnZero);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);

        btnSingleClear = findViewById(R.id.btnSingleClear);
        btnModule = findViewById(R.id.btnModule);
        btnIn = findViewById(R.id.btnIn);
        btnClear = findViewById(R.id.btnClear);
    }
}
