package com.tatvasoft.calculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import static android.os.Build.ID;
import com.tatvasoft.calculator.ui.history.model.HistoryModel;

public class HistoryDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HistoryData";
    private static final String TABLE_POST ="History";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TIMESTAMP = "blogTimePost";
    private static final String COLUMN_EXPRESSION = "expression";
    private static final String COLUMN_RESULT = "result";
    private HistoryModel dataModel;

    public HistoryDatabase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE="CREATE TABLE "+TABLE_POST+"("+COLUMN_ID+"  INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_EXPRESSION+" TEXT,"+COLUMN_RESULT+" TEXT,"+COLUMN_TIMESTAMP+" DATETIME DEFAULT CURRENT_DATE"+")";
        db.execSQL(CREATE_POST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POST);
        onCreate(db);
    }

    public ArrayList<HistoryModel> listData() {
        String sql = "select * from " + TABLE_POST;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HistoryModel> dataModel = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id=cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String expression = cursor.getString(cursor.getColumnIndex(COLUMN_EXPRESSION));
                String result=cursor.getString(cursor.getColumnIndex(COLUMN_RESULT));
                dataModel.add(new HistoryModel(id,expression, result));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataModel;
    }

    public void addBlogData(HistoryModel dataModel){
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_EXPRESSION,dataModel.getExpression());
        contentValues.put(COLUMN_RESULT,dataModel.getFinalAns());
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE_POST,null,contentValues);
    }


    public void deleteBlog() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_POST);
        db.close();
    }
}
