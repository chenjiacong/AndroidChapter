package com.example.chapter6.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chapter6.R;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SqliteActivity";
    private Button create;
    private Button update;
    private Button delete;
    private Button retrieve;
    private MyDatabaseHelper myDatabaseHelper;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initView();
        myDatabaseHelper = new MyDatabaseHelper(this, "Bookstore.db", null, 2);
    }

    private void initView() {
        create = (Button) findViewById(R.id.create);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        retrieve = (Button) findViewById(R.id.retrieve);

        create.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        retrieve.setOnClickListener(this);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.create) {
            myDatabaseHelper.getWritableDatabase();
        } else if (id == R.id.add) {
            SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "chenjiacong");
            values.put("author", "dan brown");
            values.put("pages", 454);
            values.put("price", 16.95);
            db.insert("Book", null, values);
            values.clear();
            values.put("name", "cjc");
            values.put("author", " brown");
            values.put("pages", 54);
            values.put("price", 1.95);
            db.insert("Book", null, values);
        } else if (id == R.id.update) {
            SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 66.66);
            db.update("Book", values, "name=?", new String[]{"cjc"});
        } else if (id == R.id.delete) {
            SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
            db.delete("Book", "pages=?", new String[]{"454"});
        } else if (id == R.id.retrieve) {
            SQLiteDatabase database = myDatabaseHelper.getWritableDatabase();
            Cursor cursor = database.query("Book", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Log.d(TAG, "book table name is " + name);
                    Log.d(TAG, "book table author is " + author);
                    Log.d(TAG, "book table pages is" + pages);
                    Log.d(TAG, "book table price is" + price);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }
}