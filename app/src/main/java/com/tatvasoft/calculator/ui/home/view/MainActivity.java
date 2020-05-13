package com.tatvasoft.calculator.ui.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tatvasoft.calculator.R;
import com.tatvasoft.calculator.databinding.ActivityMainBinding;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private String percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initControls();
    }

    private void initControls() {
        initListeners();

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
        binding.btnStart.setOnClickListener(this);
        binding.btnEnd.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnZero:
                appendOnExpression("0", true);
                break;
            case R.id.btnOne:
                appendOnExpression("1", true);
                break;
            case R.id.btnTwo:
                appendOnExpression("2", true);
                break;
            case R.id.btnThree:
                appendOnExpression("3", true);
                break;
            case R.id.btnFour:
                appendOnExpression("4", true);
                break;
            case R.id.btnFive:
                appendOnExpression("5", true);
                break;
            case R.id.btnSix:
                appendOnExpression("6", true);
                break;
            case R.id.btnSeven:
                appendOnExpression("7", true);
                break;
            case R.id.btnEight:
                appendOnExpression("8", true);
                break;
            case R.id.btnNine:
                appendOnExpression("9", true);
                break;
            case R.id.btnPlus:
                appendOnExpression("+", false);
                break;
            case R.id.btnMinus:
                appendOnExpression("-", false);
                break;
            case R.id.btnMul:
                appendOnExpression("*", false);
                break;
            case R.id.btnDivide:
                appendOnExpression("/", false);
                break;
            case R.id.btnModule:
                percentage="%";
                appendOnExpression("%", false);
                break;
            case R.id.btnEquals:
                calculate();
                break;
            case R.id.btnStart:
                appendOnExpression("(", false);
                break;
            case R.id.btnEnd:
                appendOnExpression(")", false);
                break;
            case R.id.btnClear:
                binding.tvMain.setText("");
                break;
            case R.id.btnDot:
                binding.tvMain.setText(binding.tvMain.getText() + ".");
                break;
            case R.id.btnSingleClear:
                singleClear();
                break;
        }
    }

    private void singleClear() {
        if (binding.tvMain.getText().length() > 0) {
            CharSequence currentText = binding.tvMain.getText();
            if (currentText.length() > 0) {
                binding.tvMain.setText(currentText.subSequence(0, currentText.length() - 1));
            } else {
                Toast.makeText(getApplicationContext(), "All cleared", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "all cleared", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculate() {
        try {
            Expression expression = new ExpressionBuilder(binding.tvMain.getText().toString()).build();
            Double result = expression.evaluate();
            long longResult =  (new Double(result)).longValue();;
            if(result .equals(Double.valueOf(longResult)))
                binding.tvResult.setText(String.valueOf(longResult));
            else
            binding.tvResult.setText(String.valueOf(result));
        } catch (Exception e) {
            Log.d("Exception", " message : " + e.getMessage());
        }
    }


    private void appendOnExpression(String string, Boolean canClear) {
        if (string.equals("%")) {
            if (percentage.equals("%")) {
                String mainValue = binding.tvMain.getText().toString();
                if (binding.tvResult.getText().length() > 0) {
                    binding.tvMain.setText("");
                }
                if (canClear) {
                    binding.tvResult.setText("");
                    binding.tvMain.append("*" + string + ")/" + 100);
                } else {
                    binding.tvMain.setText("");
//                    binding.tvMain.append(binding.tvResult.getText().toString());
                    binding.tvMain.append("(" + mainValue);
                    binding.tvResult.setText("");
                }
            } else {
                String mainValue = binding.tvMain.getText().toString();
                if (binding.tvResult.getText().length() > 0) {
                    binding.tvMain.setText("");
                }
                if (canClear) {
                    binding.tvResult.setText("");
                    binding.tvMain.append("*" + string + ")/" + 100);
                } else {
                    binding.tvMain.setText("");
//                    binding.tvMain.append(binding.tvResult.getText().toString());
                    binding.tvMain.append("(" + mainValue);
                    binding.tvResult.setText("");
                }
            }

        }else{
            if (percentage!=null) {
                if (percentage.equals("%")) {
                    String mainValue = binding.tvMain.getText().toString();
                    if (binding.tvResult.getText().length() > 0) {
                        binding.tvMain.setText("");
                    }
                    if (canClear) {
                        binding.tvResult.setText("");
                        binding.tvMain.append("*" + string + ")/" + 100);
                    } else {
                        binding.tvMain.setText("");
//                    binding.tvMain.append(binding.tvResult.getText().toString());
                        binding.tvMain.append("(" + mainValue);
                        binding.tvResult.setText("");
                }
                    percentage=null;
            }

            } else {
                String mainValue = binding.tvMain.getText().toString();
                if (binding.tvResult.getText().length() > 0) {
                    binding.tvMain.setText("");
                }
                if (canClear) {
                    binding.tvResult.setText("");
                    binding.tvMain.append(string);
                } else {
                    binding.tvMain.append(binding.tvResult.getText().toString());
                    binding.tvMain.append( mainValue);
                    binding.tvResult.setText("");
                }
            }
        }
    }
}
