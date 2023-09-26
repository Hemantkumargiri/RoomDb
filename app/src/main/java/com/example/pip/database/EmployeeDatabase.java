package com.example.pip.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.pip.dao.EmployeeDao;
import com.example.pip.model.Converters;
import com.example.pip.model.Data;
import com.example.pip.model.EmployeeDataResponse;

@Database(entities = {Data.class,}, version = 3,exportSchema = false)
@TypeConverters(Converters.class)
public abstract class EmployeeDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "EmployeeDatabase";

    public abstract EmployeeDao employeeDao();

    private static volatile EmployeeDatabase INSTANCE;

    public static EmployeeDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, EmployeeDatabase.class,
                                    DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }

    };

    static class PopulateAsynTask extends AsyncTask<Void,Void,Void>
    {
        private EmployeeDao employeeDao;
        PopulateAsynTask(EmployeeDatabase employeeDatabase)
        {
            employeeDao=employeeDatabase.employeeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}
