package com.mad.vandolf.contentprovidertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView resultView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        resultView = (TextView) findViewById(R.id.res);
    }

    public void onClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(MyProvider.name, ((EditText) findViewById(R.id.txtName)).getText().toString());
        Uri uri = getContentResolver().insert(MyProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), "New record inserted " + uri, Toast.LENGTH_LONG).show();
    }

    public void onClickDisplayNames(View view) {
        Cursor cursor = getContentResolver().query(MyProvider.CONTENT_URI, null, null,null, "");
        cursor.moveToFirst();
        StringBuilder res = new StringBuilder();
        while (!cursor.isAfterLast()) {
            res.append("\n" + cursor.getString(cursor.getColumnIndex("id")) + "-" + cursor.getString(cursor.getColumnIndex("name")));
            cursor.moveToNext();
        }
        resultView.setText(res);
    }

    public void onClickDeleteName(View view) {
        String nama = ((EditText) findViewById(R.id.txtName)).getText().toString();
        int jumlah = getContentResolver().delete(MyProvider.CONTENT_URI, "name=?", new String[]{nama});
        if(jumlah > 0) {
            Toast.makeText(getBaseContext(), "Data dengan Nama: " + nama + " telah dihapus, jumlah data: " + jumlah, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "Data yang ingin dihapus tidak ada", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickGetType(View view) {
        String tipe = getContentResolver().getType(MyProvider.CONTENT_URI);
        Toast.makeText(getBaseContext(), "Provider type: " + tipe, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
