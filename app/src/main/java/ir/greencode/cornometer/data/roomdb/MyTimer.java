package ir.greencode.cornometer.data.roomdb;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * Created by alireza on 5/19/18.
 */
@Entity(tableName = "timer")
public class MyTimer {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;
    String title;
    String desc;
    int type ; //1 == countdown 2==twostep
    long waitingTime;
    long time;
    public MyTimer(String title, String desc, int type,long waitingTime,long time) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.waitingTime = waitingTime;
        this.time = time;
    }

    public long getwaitingTime() {
        return waitingTime;
    }

    public long gettime() {
        return time;
    }

    public int getid() {
        return id;
    }

    public String gettitle() {
        return title;
    }

    public String getdesc() {
        return desc;
    }

    public int gettype() {
        return type;
    }
}
