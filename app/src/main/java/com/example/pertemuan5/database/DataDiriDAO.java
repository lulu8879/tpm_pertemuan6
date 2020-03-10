/*
 * Creator  : Sakti Wicaksono
 * Class    : DataDiri DAO CRUD
 * Date     : 10-03-2020
 */

package com.example.pertemuan5.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DataDiriDAO {
    @Insert
    Long insertData(DataDiri dataDiri);

    @Query("SELECT * FROM datadiri_db")
    DataDiri[] getData();

    @Update
    int updateData(DataDiri dataDiri);

    @Delete
    void deleteData(DataDiri dataDiri);
}
