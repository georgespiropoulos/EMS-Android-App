package ihuiee.webservices.ems_android_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HospitalsAdapter extends RecyclerView.Adapter<HospitalsAdapter.ViewHolder> {

    private String[] hospitalClinics;
    private String[] hospitalNames;
    private Context context;

    public HospitalsAdapter(String[] hospitalClinics, String[] hospitalNames, Context context) {
        this.hospitalClinics = hospitalClinics;
        this.hospitalNames = hospitalNames;
        this.context = context;
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
        holder.hospitalName.setText(hospitalNames[position]);
        holder.hospitalClinics.setText(hospitalClinics[position]);
    }

    @Override
    public int getItemCount() {
        return hospitalNames.length;
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
