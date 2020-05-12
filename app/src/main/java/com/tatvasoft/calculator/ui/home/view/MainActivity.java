package com.tatvasoft.calculator.ui.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.net.wifi.aware.SubscribeConfig;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tatvasoft.calculator.R;
import com.tatvasoft.calculator.databinding.ActivityMainBinding;
import com.tatvasoft.calculator.util.CommonUtils;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char REMAINDER = '%';
    private char CURRENT_ACTION;
    private double valueOne = Double.NaN;
    private double valueTwo;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decimalFormat = new DecimalFormat("#.##########");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initControls();
    }

    private void initControls() {
        initListeners();
    }


    @SuppressLint("SetTextI18n")
    private void edNumbers(String value) {
        binding.edMain.setText(binding.edMain.getText() + value);
    }

    private void initListeners() {

        binding.btnDot.setOnClickListener(this);
        binding.btnDivide.setOnClickListener(this);
        binding.btnEquals.setOnClickListener(this);
        binding.btnPlus.setOnClickListener(this);
        binding.btnMinus.setOnClickListener(this);
        binding.btnMul.setOnClickListener(this);

        binding.btnZero.setOnClickListener(this);
        binding.btnOne.setOnClickListener(this);
        binding.btnTwo.setOnClickListener(this);
        binding.btnThree.setOnClickListener(this);
        binding.btnFour.setOnClickListener(this);
        binding.btnFive.setOnClickListener(this);
        binding.btnSix.setOnClickListener(this);
        binding.btnSeven.setOnClickListener(this);
        binding.btnEight.setOnClickListener(this);
        binding.btnNine.setOnClickListener(this);

        binding.btnSingleClear.setOnClickListener(this);
        binding.btnModule.setOnClickListener(this);
        binding.btnIn.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
        binding.edMain.requestFocus();

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnZero:
                edNumbers("0");
                break;
            case R.id.btnOne:
                edNumbers("1");
                break;
            case R.id.btnTwo:
                edNumbers("2");
                break;
            case R.id.btnThree:
                edNumbers("3");
                break;
            case R.id.btnFour:
                edNumbers("4");
                break;
            case R.id.btnFive:
                edNumbers("5");
                break;
            case R.id.btnSix:
                edNumbers("6");
                break;
            case R.id.btnSeven:
                edNumbers("7");
                break;
            case R.id.btnEight:
                edNumbers("8");
                break;
            case R.id.btnNine:
                edNumbers("9");
                break;
            case R.id.btnPlus:
                computeCalculation();
                CURRENT_ACTION = ADDITION;
                binding.tvMain.setText(decimalFormat.format(valueOne) + "+");
                binding.edMain.setText(null);
                break;
            case R.id.btnMinus:
                computeCalculation();
                CURRENT_ACTION = SUBTRACTION;
                binding.tvMain.setText(decimalFormat.format(valueOne) + "-");
                binding.edMain.setText(null);
                break;
            case R.id.btnMul:
                computeCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                binding.tvMain.setText(decimalFormat.format(valueOne) + "*");
                binding.edMain.setText(null);
                break;
            case R.id.btnDivide:
                computeCalculation();
                CURRENT_ACTION = DIVISION;
                binding.tvMain.setText(decimalFormat.format(valueOne) + "/");
                binding.edMain.setText(null);
                break;
            case R.id.btnModule:
                computeCalculation();
                CURRENT_ACTION = REMAINDER;
                binding.tvMain.setText(decimalFormat.format(valueOne) + "%");
                binding.edMain.setText(null);
                break;
            case R.id.btnEquals:
                computeCalculation();
                binding.tvMain.setText(decimalFormat.format(valueOne));
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
                break;
            case R.id.btnClear:
                valueOne = Double.NaN;
                valueTwo = Double.NaN;
                binding.edMain.setText("");
                binding.tvMain.setText("");
                break;
            case R.id.btnDot:
//                if (decimal) {
//                    Toast.makeText(getApplicationContext(),"Add some values",Toast.LENGTH_SHORT).show();
//                } else {
//                    edMain.setText(edMain.getText() + ".");
//                    decimal = true;
//                }
                break;
            case R.id.btnSingleClear:
                if (binding.edMain.getText().length() > 0) {
                    CharSequence currentText = binding.edMain.getText();
                    binding.edMain.setText(currentText.subSequence(0, currentText.length() - 1));
                }
                break;
        }
    }

    private void computeCalculation() {
        if (!Double.isNaN(valueOne)) {
            if (CommonUtils.isEmptyEditText(binding.edMain)){
                Toast.makeText(getApplicationContext(),"Add value",Toast.LENGTH_SHORT).show();
            }else {
                valueTwo = Double.parseDouble(binding.edMain.getText().toString());
                binding.edMain.setText(null);
                if (CURRENT_ACTION == ADDITION)
                    valueOne = this.valueOne + valueTwo;
                else if (CURRENT_ACTION == SUBTRACTION)
                    valueOne = this.valueOne - valueTwo;
                else if (CURRENT_ACTION == MULTIPLICATION)
                    valueOne = this.valueOne * valueTwo;
                else if (CURRENT_ACTION == DIVISION)
                    valueOne = this.valueOne / valueTwo;
            }
        } else {
            try {
                valueOne = Double.parseDouble(binding.edMain.getText().toString());
            } catch (Exception e) {
            }
        }
    }
}
