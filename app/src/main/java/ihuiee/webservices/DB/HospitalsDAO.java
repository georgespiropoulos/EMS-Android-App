package ihuiee.webservices.DB;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;

@Dao
public interface HospitalsDAO {
    @Query("SELECT * FROM hospitals WHERE name_hospital = :name_hospital")
    Hospitals getHospitalName(String name_hospital);

    @Query("SELECT * FROM hospitals")
    LiveData<List<Hospitals>> getAll();

    @Insert
    void insertAll(Hospitals hospital);

    @Delete
    void deleteAll(Hospitals hospital);
}
