package ihuiee.webservices.ems_android_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ihuiee.webservices.Crawler.Crawler;
import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;
import ihuiee.webservices.DB.HospitalsViewModel;

public class MainActivity extends AppCompatActivity {

    private HospitalsAdapter adapter;
    private List<Hospitals> hospitalsFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        Content content = new Content();
//        content.execute();

        Fragment frag = new LandingPage();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, frag)
                .commit();

        (new Handler()).postDelayed((Runnable) () -> {
            Fragment fragment = new LandingPageButton();
            getSupportFragmentManager().beginTransaction()
//                    .setCustomAnimations(androidx.transition.R.anim.fragment_fade_enter, androidx.transition.R.anim.fragment_fade_exit)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }, 2500);


        /*RecyclerView rc = findViewById(R.id.hospitalList);
        rc.setLayoutManager(new LinearLayoutManager(this));

        hospitalsFromDB = AppDatabase.getInstance(this).hospitalsDao().getAll();

        Content content = new Content();
        content.execute();

        adapter = new HospitalsAdapter(hospitalsFromDB, this);
        rc.setAdapter(adapter);*/

    }

//    private class Content extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            Crawler cwr = new Crawler("Θεσσαλονίκη");
//
//            cwr.getHospitalsFroCrawler();
//
//            Log.d("Crawler", "Hospitals and Clinics");
//            return null;
//        }
//    }
}