package ihuiee.webservices.ems_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ihuiee.webservices.Crawler.Crawler;
import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;

public class MainActivity extends AppCompatActivity {

    private List<String> hospitalClinics = new ArrayList<>();
    private List<String> hospitalNames = new ArrayList<>();
    private HospitalsAdapter adapter;
    private List<Hospitals> hospitalsFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_starting_page_content);

        RecyclerView rc = findViewById(R.id.hospitalList);
        rc.setLayoutManager(new LinearLayoutManager(this));
        Content content = new Content();
        content.execute();

        hospitalsFromDB = AppDatabase.getInstance(getApplicationContext()).hospitalsDao().getAll();

        for (int i = 0; i < hospitalsFromDB.size(); i++) {
           hospitalNames.add(hospitalsFromDB.get(i).nameOfHospital);
           hospitalClinics.add(hospitalsFromDB.get(i).clinicsOfHospital);
        }

        adapter = new HospitalsAdapter(hospitalClinics, hospitalNames, this);
        rc.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HashMap<String, ArrayList<String>> firstMap;

            try {
                Crawler cwr = new Crawler("Θεσσαλονίκη");
                //firstMap = cwr.getAvailableClinics();

                cwr.fillDB(getApplicationContext());

                /*for (int i = 0; i < firstMap.size(); i++) {
                    hospitalNames[i] = firstMap.keySet().toArray(new String[0])[i];
                    hospitalClinics[i] = firstMap.get(firstMap.keySet().toArray(new String[0])[i]).get(0);
                }*/
                Log.d("Crawler", "Hospitals and Clinics");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}