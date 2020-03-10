/*
 * Creator  : Sakti Wicaksono
 * Class    : Main Activity
 * Date     : 10-03-2020
 */

package com.example.pertemuan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pertemuan5.database.AppDatabase;
import com.example.pertemuan5.database.DataDiri;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private EditText et_name, et_address, et_gender;
    private Button btn_insert, btn_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init DB
        appDatabase = AppDatabase.initDB(this);

        et_name = findViewById(R.id.activitymain_et_name);
        et_address = findViewById(R.id.activitymain_et_address);
        et_gender = findViewById(R.id.activitymain_et_gender);
        btn_insert = findViewById(R.id.activitymain_btn_submit);
        btn_list = findViewById(R.id.activitymain_btn_list);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

    }

    private void readData() {
        Intent intent = new Intent(getApplicationContext(),ListActivity.class);
        startActivity(intent);
    }

    private void insertData() {
        String name = et_name.getText().toString();
        String address = et_address.getText().toString();
        char gender = et_gender.getText().toString().charAt(0);

        DataDiri dataDiri = new DataDiri();
        dataDiri.setName(name);
        dataDiri.setAddress(address);
        dataDiri.setGender(gender);
        appDatabase.dataDiriDAO().insertData(dataDiri);
        Toast.makeText(getApplicationContext(),"Berhasil menginputkan data!",Toast.LENGTH_SHORT).show();

        et_name.setText("");
        et_address.setText("");
        et_gender.setText("");
    }
}
