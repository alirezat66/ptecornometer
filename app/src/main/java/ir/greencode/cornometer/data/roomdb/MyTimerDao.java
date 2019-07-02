package ir.greencode.cornometer.data.roomdb;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MyTimerDao {

    @Query("select * from  timer")
    List<MyTimer> listOfTimers();

    @Query("select * from timer where id= :id limit 1")
    MyTimer specialCat(int id);

    @Insert(onConflict = REPLACE)
    void insertTimer(MyTimer category);

    @Query("DELETE FROM timer")
    public void nukeTable();
}
