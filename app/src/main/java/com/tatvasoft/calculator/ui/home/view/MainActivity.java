package com.tatvasoft.calculator.ui.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.tatvasoft.calculator.R;
import com.tatvasoft.calculator.adapter.HistoryAdapter;
import com.tatvasoft.calculator.database.HistoryDatabase;
import com.tatvasoft.calculator.databinding.ActivityMainBinding;
import com.tatvasoft.calculator.ui.history.model.HistoryModel;
import com.tatvasoft.calculator.ui.history.view.HistoryActivity;
import com.tatvasoft.calculator.util.CommonUtils;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private HistoryDatabase historyDatabase;
    private String process;
    private boolean checkBracket = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initControls();
    }

    private void initControls() {
        initListeners();
        onTextChange();

    }

    private void initListeners() {
        historyDatabase = new HistoryDatabase(this);

        CommonUtils.stethoInitialize(getApplicationContext());

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
        binding.btnClear.setOnClickListener(this);

        binding.btnHistory.setOnClickListener(this);

    }

    @SuppressLint("SetTextI18n")
    private void onTextChange() {
        if (!(binding.tvMain.length() > 0)) {
            int size = historyDatabase.listData().size();
            if (size > 0) {
                binding.tvResult.setText(historyDatabase.listData().get(size - 1).getExpression() + " = " + historyDatabase.listData().get(size - 1).getFinalAns());
            }
        }
        binding.tvMain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!(charSequence.length() > 0)) {
                    int size = historyDatabase.listData().size();
                    if (size > 0) {
                        binding.tvResult.setText(historyDatabase.listData().get(size - 1).getExpression() + " = " + historyDatabase.listData().get(size - 1).getFinalAns());
                    }
                }
                if (charSequence.length() > 0) {
                    if ((charSequence.charAt(i) == '+') || (charSequence.charAt(i) == '-') || (charSequence.charAt(i) == '/') || (charSequence.charAt(i) == 'x') || (charSequence.charAt(i) == '%')) {
                        binding.tvMain.setText("");
                        Toast.makeText(getApplicationContext(), "invalid input", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnZero:
                setText("0");
                break;
            case R.id.btnOne:
                setText("1");
                break;
            case R.id.btnTwo:
                setText("2");
                break;
            case R.id.btnThree:
                setText("3");
                break;
            case R.id.btnFour:
                setText("4");
                break;
            case R.id.btnFive:
                setText("5");
                break;
            case R.id.btnSix:
                setText("6");
                break;
            case R.id.btnSeven:
                setText("7");
                break;
            case R.id.btnEight:
                setText("8");
                break;
            case R.id.btnNine:
                setText("9");
                break;
            case R.id.btnPlus:
                setText("+");
                break;
            case R.id.btnMinus:
                setText("-");
                break;
            case R.id.btnMul:
                setText("x");
                break;
            case R.id.btnDivide:
                setText("/");
                break;
            case R.id.btnModule:
                setText("%");
                break;
            case R.id.btnEquals:
                calculate(binding.tvMain.getText().toString().trim());
                break;
            case R.id.btnStart:
                if (checkBracket) {
                    setText(")");
                    checkBracket = false;
                } else {
                    setText("(");
                    checkBracket = true;
                }
                break;
            case R.id.btnClear:
                binding.tvMain.setText("");
                binding.tvResult.setText("");
                break;
            case R.id.btnDot:
                binding.tvMain.setText(binding.tvMain.getText() + ".");
                break;
            case R.id.btnSingleClear:
                singleClear();
                break;
            case R.id.btnHistory:
                showHistory();
                break;
        }
    }

    private void showHistory() {
        startActivityForResult(new Intent(getApplicationContext(), HistoryActivity.class), 0);
    }

    @SuppressLint("SetTextI18n")
    private void calculate(String s) {
        process = s;

        process = process.replaceAll("x", "*");
        process = process.replaceAll("%", "/100*");

        Context expression = Context.enter();
        expression.setOptimizationLevel(-1);
        String finalResult = "";

        try {
            Scriptable scriptable = expression.initSafeStandardObjects();
            finalResult = expression.evaluateString(scriptable, process, "javascript", 1, null).toString();
            process = process.replaceAll(getString(R.string.percentage), "%");
            historyDatabase.addBlogData(new HistoryModel(0, process, finalResult));
        } catch (Exception e) {
            finalResult = "0";
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        binding.tvResult.setText(process + " = " + finalResult);
    }

    private void singleClear() {
//        checkBracket = !checkBracket;
        if (binding.tvMain.getText().length() > 0) {
            checkBracket = !checkBracket;
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

    @SuppressLint("SetTextI18n")
    public void setText(String value) {
        process = binding.tvMain.getText().toString();
        binding.tvMain.setText(process + value);
        if (binding.tvResult.getText().length() > 0) {
            binding.tvMain.setText("");
            binding.tvResult.setText("");
            binding.tvMain.append(value);
        }
    }

}
