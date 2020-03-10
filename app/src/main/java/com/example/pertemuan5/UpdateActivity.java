/*
 * Creator  : Sakti Wicaksono
 * Class    : Update Activity
 * Date     : 10-03-2020
 */

package com.example.pertemuan5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pertemuan5.database.AppDatabase;
import com.example.pertemuan5.database.DataDiri;

public class UpdateActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private EditText et_name, et_address, et_gender;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        appDatabase = AppDatabase.initDB(this);

        et_name = findViewById(R.id.activityupdate_et_name);
        et_address = findViewById(R.id.activityupdate_et_address);
        et_gender = findViewById(R.id.activityupdate_et_gender);
        btn_update = findViewById(R.id.activityupdate_btn_update);

        final int id = getIntent().getIntExtra("id",0);
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        char gender = getIntent().getCharExtra("gender", 'N');

        et_name.setText(name);
        et_address.setText(address);
        et_gender.setText(new char[]{gender},0,1);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(id);
            }
        });
    }

    private void updateData(int id) {
        String name = et_name.getText().toString();
        String address = et_address.getText().toString();
        char gender = et_gender.getText().toString().charAt(0);

        DataDiri dataDiri = new DataDiri();
        dataDiri.setId(id);
        dataDiri.setName(name);
        dataDiri.setAddress(address);
        dataDiri.setGender(gender);
        appDatabase.dataDiriDAO().updateData(dataDiri);
        Toast.makeText(getApplicationContext(),"Berhasil mengupdate data!",Toast.LENGTH_SHORT).show();

        // Delay 1 second then refresh list
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UpdateActivity.this.finish();
                new ListActivity().refresh();
            }
        }, 1000);
    }
}
