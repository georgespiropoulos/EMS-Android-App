package ihuiee.webservices.ems_android_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;

public class HospitalsAdapter extends RecyclerView.Adapter<HospitalsAdapter.ViewHolder> {

    private List<Hospitals> hospitals;
    private Context context;

    public HospitalsAdapter(List<Hospitals> hospitals, Context context) {
        this.context = context;
        this.hospitals = hospitals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_hospital_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hospitalName.setText(hospitals.get(position).nameOfHospital);
        holder.hospitalClinics.setText(hospitals.get(position).clinicsOfHospital);

        holder.itemView.findViewById(R.id.hospital_card).setOnClickListener(view -> {
            switch (holder.hospitalName.getVisibility()) {

                case View.GONE:
                    break;
                case View.INVISIBLE:
                    holder.hospitalName.setVisibility(View.VISIBLE);
                    holder.hospitalClinics.setVisibility(View.INVISIBLE);
                    break;
                case View.VISIBLE:
                    holder.hospitalName.setVisibility(View.INVISIBLE);
                    holder.hospitalClinics.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalName;
        TextView hospitalClinics;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospital0);
            hospitalClinics = itemView.findViewById(R.id.hospitalClinics0);
        }
    }
}
