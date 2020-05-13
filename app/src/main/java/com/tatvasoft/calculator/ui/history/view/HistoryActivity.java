package com.tatvasoft.calculator.ui.history.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tatvasoft.calculator.R;
import com.tatvasoft.calculator.adapter.HistoryAdapter;
import com.tatvasoft.calculator.database.HistoryDatabase;
import com.tatvasoft.calculator.databinding.ActivityHistoryBinding;
import com.tatvasoft.calculator.ui.history.model.HistoryModel;
import com.tatvasoft.calculator.util.CommonUtils;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayoutManager linearLayoutManager;
    private HistoryDatabase historyDatabase;
    private ArrayList<HistoryModel> histories;
    private ActivityHistoryBinding binding;
    private HistoryAdapter detailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history);
        iniControls();
    }

    private void iniControls() {
        initListeners();
        CommonUtils.stethoInitialize(getApplicationContext());
        getHistory();
    }

    private void getHistory() {
        histories = historyDatabase.listData();
        if (histories.size() > 0) {
            detailsAdapter = new HistoryAdapter(getApplicationContext(),histories);
            binding.rvHistory.setAdapter(detailsAdapter);
        } else {
            detailsAdapter = new HistoryAdapter(getApplicationContext(),histories);
            binding.rvHistory.setAdapter(detailsAdapter);
            Toast.makeText(getApplicationContext(), "no history", Toast.LENGTH_LONG).show();
        }
    }

    private void initListeners() {
        historyDatabase = new HistoryDatabase(this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvHistory.setLayoutManager(linearLayoutManager);
        binding.btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if (id==R.id.btnClear){
            clearAll();
        }
    }

    private void clearAll() {
        historyDatabase.deleteBlog();
        getHistory();
    }
}
