package ihuiee.webservices.ems_android_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import ihuiee.webservices.Crawler.Crawler;

public class HospitalsAdapter extends RecyclerView.Adapter<HospitalsAdapter.ViewHolder> {

    Crawler cwr = new Crawler("thessalonikh");
    HashMap<String, ArrayList<String>> hospitalMap = cwr.getAvailableClinics();
    String[] keyList = hospitalMap.keySet().toArray(new String[0]); // new String[hospitalMap.size()]

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_hospital_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hostpitalName.setText(keyList[position]);
        holder.hospitalClinics.setText(Objects.requireNonNull(hospitalMap.get(keyList[position])).get(0));
        hospitalMap.get(keyList[position]);
    }

    @Override
    public int getItemCount() {
        return hospitalMap.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hostpitalName;
        TextView hospitalClinics;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hostpitalName = itemView.findViewById(R.id.hospital0);
            hospitalClinics = itemView.findViewById(R.id.hospitalClinics0);
        }
    }
}
