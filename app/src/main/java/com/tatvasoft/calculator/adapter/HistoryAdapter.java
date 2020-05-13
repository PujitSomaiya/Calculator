package com.tatvasoft.calculator.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tatvasoft.calculator.R;
import com.tatvasoft.calculator.database.HistoryDatabase;
import com.tatvasoft.calculator.ui.history.model.HistoryModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {


    private final Context context;
    private final ArrayList<HistoryModel> id;
    private String dataExpression;
    private String dataResult;
    private HistoryModel dataId;
    private int positionOfData;
    private Context rootContext;
    private View view;

    public HistoryAdapter(Context context, ArrayList<HistoryModel> expression) {
        this.context = context;
        this.id = expression;
        rootContext = context;

    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.list_history, parent, false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryViewHolder holder, final int position) {
        if (id == null) {
            check();
        } else {
            dataId = id.get(position);
            {
                holder.tvExpression.setText(dataId.getExpression());
                holder.tvResult.setText(dataId.getFinalAns());
                holder.tvId.setText(String.valueOf(dataId.getId()));
            }
        }

    }

    private void check() {
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return id.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvId, tvExpression, tvResult;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvExpression = itemView.findViewById(R.id.tvExpression);
            tvResult = itemView.findViewById(R.id.tvResult);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

