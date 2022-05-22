package ihuiee.webservices.DB;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HospitalsRepository {

    private HospitalsDAO mHospitalsDao;
    private LiveData<List<Hospitals>> mAllHospitals;

    public HospitalsRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mHospitalsDao = db.hospitalsDao();
        mAllHospitals = mHospitalsDao.getAll();
    }

    LiveData<List<Hospitals>> getAllHospitals() {
        return mAllHospitals;
    }

    void insert(Hospitals hospital) {
        AppDatabase.databaseWriterExecutor.execute(() -> {
            mHospitalsDao.insertAll(hospital);
        });
    }
}
