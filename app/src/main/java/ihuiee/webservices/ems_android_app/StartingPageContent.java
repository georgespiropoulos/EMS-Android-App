package ihuiee.webservices.ems_android_app;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ihuiee.webservices.Crawler.Crawler;
import ihuiee.webservices.DB.Hospitals;

public class StartingPageContent extends Fragment {

    HospitalsAdapter adapter;
    List<Hospitals> listOfHospitals = new ArrayList<>();

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
            Thread t1 = new Thread(new Crawler("thessalonikh", listOfHospitals));

            t1.start();
            t1.join();
            //listOfHospitals = tCrawler.getListOfHospitals();
            System.out.println(listOfHospitals);
            List<Hospitals> fixedList = new ArrayList<>();
            for (Hospitals item : listOfHospitals) {
                if (!fixedList.contains(item))
                    fixedList.add(item);
            }
            adapter = new HospitalsAdapter(getContext(), fixedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            adapter = new HospitalsAdapter(getContext(), new Content().execute().get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }*/

        rc.setAdapter(adapter);

        ImageButton emergencyBtn = view.findViewById(R.id.emergencyButton);
        emergencyBtn.setOnClickListener(v -> {
            FragmentManager manager;
            assert getParentFragment() != null;
            manager = getParentFragment().getParentFragmentManager();
            Fragment emergency = new EmergencyQuestions();
            manager.beginTransaction()
                    .replace(R.id.fragment_container, emergency)
                    .commit();

        });
    }


    /*private class Content extends AsyncTask<Void, Void, List<Hospitals>> {

        @Override
        protected List<Hospitals> doInBackground(Void... voids) {


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
    }*/
}

