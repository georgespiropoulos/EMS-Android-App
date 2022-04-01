package ihuiee.webservices.DB;

import androidx.room.*;

@Database(entities = {Hospitals.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();

    public abstract HospitalsDAO hospitalsDao();
}
