package ihuiee.webservices.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ihuiee.webservices.Crawler.Crawler;

@Database(entities = {Hospitals.class, User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "ems_db2";
    private static AppDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    static ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    //.addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return instance;
    }

    public abstract UserDAO userDao();

    public abstract HospitalsDAO hospitalsDao();

    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriterExecutor.execute(() -> {
                HospitalsDAO dao = instance.hospitalsDao();

//                Crawler cwl = new Crawler("Θεσσαλονίκη");
//                for (Hospitals hospital : cwl.getHospitalsFromCrawler()) {
//                    dao.insertAll(hospital);
//                }
            });
        }
    };
}