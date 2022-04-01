package ihuiee.webservices.DB;

import androidx.room.*;
import java.util.List;

@Dao
public interface HospitalsDAO {
    @Query("SELECT * FROM hospitals")
    List<Hospitals> getAll();

    @Insert
    void insertAll(Hospitals hospital);

    @Delete
    void delete(Hospitals hospital);
}
