package ihuiee.webservices.ems_android_app;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import ihuiee.webservices.Crawler.Crawler;
import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;
import ihuiee.webservices.DB.HospitalsViewModel;

public class StartingPageContent extends Fragment {

    HospitalsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_starting_page_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView onDutyCity = view.findViewById(R.id.onDutyCity);
        onDutyCity.setText("Θεσσαλονίκη");

        RecyclerView rc = view.findViewById(R.id.hospitalList);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            adapter = new HospitalsAdapter(getContext(), new Content().execute().get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        rc.setAdapter(adapter);

        ImageButton emegerncyBtn = view.findViewById(R.id.emergencyButton);
        emegerncyBtn.setOnClickListener(v -> {
            FragmentManager manager;
            assert getParentFragment() != null;
            manager = getParentFragment().getParentFragmentManager();
            Fragment emergency = new EmergencyQuestions();
            manager.beginTransaction()
                    .replace(R.id.fragment_container, emergency)
                    .commit();

        });
    }


    @SuppressLint("StaticFieldLeak")
    private class Content extends AsyncTask<Void, Void, List<Hospitals>> {

        @Override
        protected List<Hospitals> doInBackground(Void... voids) {
            Crawler cwr = new Crawler("thessalonikh");

            List<Hospitals> hospitalsFromCwr = cwr.getHospitalsFromCrawler();

            Log.d("Crawler", "Hospitals and Clinics");

            return hospitalsFromCwr;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void onPostExecute(List<Hospitals> hospitals) {
            super.onPostExecute(hospitals);
            adapter.allHospitals = hospitals;
            adapter.notifyDataSetChanged();
        }
    }
}

