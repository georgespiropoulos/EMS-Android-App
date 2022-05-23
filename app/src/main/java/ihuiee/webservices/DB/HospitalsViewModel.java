package ihuiee.webservices.DB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HospitalsViewModel extends AndroidViewModel {

    private HospitalsRepository mRepository;
    private LiveData<List<Hospitals>> mAllHospitals;

    public HospitalsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new HospitalsRepository(application);
        mAllHospitals = mRepository.getAllHospitals();
    }

    LiveData<List<Hospitals>> getAllHospitals() {
        return mAllHospitals;
    }

    void insert(Hospitals hospital) {
        mRepository.insert(hospital);
    }
}
