/*
 * Creator  : Sakti Wicaksono
 * Class    : List Activity (Read Data)
 * Date     : 10-03-2020
 */

package com.example.pertemuan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.pertemuan5.database.AppDatabase;
import com.example.pertemuan5.database.DataDiri;

public class ListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private AppDatabase appDatabase;
    private DataDiri[] dataDiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        appDatabase = AppDatabase.initDB(this);
        rvList = findViewById(R.id.activitylist_rv);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        dataDiris = appDatabase.dataDiriDAO().getData();

        readList();
    }

    private void readList() {
        DataDiriAdapter dataDiriAdapter = new DataDiriAdapter(getApplicationContext(), dataDiris, new DataDiriListener() {
            @Override
            public void onButtonDelete(DataDiri dataDiri) {
                appDatabase.dataDiriDAO().deleteData(dataDiri);
                Toast.makeText(getApplicationContext(),"Berhasil menghapus data!",Toast.LENGTH_SHORT).show();
                readList();
            }
        });
        rvList.setAdapter(dataDiriAdapter);
    }

    public void refresh() {
        readList();
    }
}
