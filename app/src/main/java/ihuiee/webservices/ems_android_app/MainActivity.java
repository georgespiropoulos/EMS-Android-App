package ihuiee.webservices.ems_android_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ihuiee.webservices.Crawler.Crawler;
import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;

public class MainActivity extends AppCompatActivity {

    private HospitalsAdapter adapter;
    private List<Hospitals> hospitalsFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_starting_page_content);

        RecyclerView rc = findViewById(R.id.hospitalList);
        rc.setLayoutManager(new LinearLayoutManager(this));

        hospitalsFromDB = AppDatabase.getInstance(this).hospitalsDao().getAll();

        Content content = new Content();
        content.execute();

        adapter = new HospitalsAdapter(hospitalsFromDB, this);
        rc.setAdapter(adapter);
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Crawler cwr = new Crawler("Θεσσαλονίκη");

            cwr.fillDB(getApplicationContext());

            Log.d("Crawler", "Hospitals and Clinics");
            return null;
        }
    }
}