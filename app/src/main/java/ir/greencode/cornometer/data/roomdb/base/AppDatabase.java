package ir.greencode.cornometer.data.roomdb.base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ir.greencode.cornometer.data.roomdb.MyTimer;
import ir.greencode.cornometer.data.roomdb.MyTimerDao;
import ir.greencode.cornometer.utility.Constant;

@Database(entities = {MyTimer.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract MyTimerDao categoryDao();
    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constant.DB_NAME)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
