package ihuiee.webservices.ems_android_app;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import ihuiee.webservices.DB.AppDatabase;
import ihuiee.webservices.DB.Hospitals;

public class HospitalsAdapter extends RecyclerView.Adapter<HospitalsAdapter.ViewHolder> {
    List<Hospitals> allHospitals;
    private Context context;

    public HospitalsAdapter(Context context, List<Hospitals> hospitals) {
        this.context = context;
        this.allHospitals = hospitals;
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
        holder.hospitalName.setText(allHospitals.get(position).nameOfHospital);
        holder.hospitalClinics.setText(allHospitals.get(position).clinicsOfHospital);

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

        holder.hospitalLocation.setOnClickListener(view -> {
            Toast.makeText(context, allHospitals.get(position).hospitalAddress, Toast.LENGTH_LONG).show();
            // geo:0,0?q=
            Geocoder geocoder = new Geocoder(context);
            try {
                List<Address> geoResults = geocoder.getFromLocationName(allHospitals.get(position).hospitalAddress, 1);
                Address addr = geoResults.get(0);
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", addr.getLatitude(), addr.getLongitude());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                Intent chooser = Intent.createChooser(intent, "Launch Maps");
                context.startActivity(chooser);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allHospitals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalName;
        TextView hospitalClinics;
        ImageView hospitalLocation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospital0);
            hospitalClinics = itemView.findViewById(R.id.hospitalClinics0);
            hospitalLocation = itemView.findViewById(R.id.hospital_location);
        }
    }
}
