//package ihuiee.webservices.DB;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import ihuiee.webservices.Crawler.Crawler;
//
//@Database(entities = {Hospitals.class, User.class}, version = 1, exportSchema = false)
//public abstract class HospitalDatabase extends RoomDatabase {
//    private static final String DB_NAME = "ems_db";
//    private static HospitalDatabase instance;
//    private static final int NUMBER_OF_THREADS = 4;
//    static ExecutorService databaseWriterExecutor =
//            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//    public static synchronized HospitalDatabase getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(context.getApplicationContext(), HospitalDatabase.class, DB_NAME)
//                    .addCallback(sRoomDatabaseCallback)
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return instance;
//    }
//
//    public abstract UserDAO userDao();
//
//    public abstract HospitalsDAO hospitalsDao();
//
//    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            databaseWriterExecutor.execute(() -> {
//                HospitalsDAO dao = instance.hospitalsDao();
//
//                Crawler cwl = new Crawler("Θεσσαλονίκη");
//                for (Hospitals hospital : cwl.getHospitalsFroCrawler()) {
//                    dao.insertAll(hospital);
//                }
//            });
//        }
//    };
//
//}