package com.example.doctorsearchapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorsearchapp.MainActivity;
import com.example.doctorsearchapp.R;
import com.example.doctorsearchapp.fragments.DetailFragment;
import com.example.doctorsearchapp.models.Doctor;

import org.parceler.Parcels;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    Context context;
    List<Doctor> doctors;

    public DoctorAdapter(Context context, List<Doctor> doctors) {
        this.context = context;
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View doctorView = LayoutInflater.from(context).inflate(R.layout.item_doctor, parent, false);
        return new ViewHolder(doctorView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.bind(doctor);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send doctor object to DetailFragment/DoctorAdapter
                DetailFragment detailFragment = new DetailFragment();
                Bundle b = new Bundle();
                b.putParcelable("doctor", doctor);
                detailFragment.setArguments(b);

                MainActivity activity = (MainActivity) context;
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, detailFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rvSearchResults;
        TextView tvDoctorName;
        TextView tvLocation;
        RatingBar rbOverallRating;

        public ViewHolder(View doctorView) {
            super(doctorView);
            rvSearchResults = doctorView.findViewById(R.id.rvSearchResults);
            tvDoctorName = doctorView.findViewById(R.id.tvDoctorName);
            tvLocation = doctorView.findViewById(R.id.tvLocation);
            rbOverallRating = doctorView.findViewById(R.id.rbDoctorRating);
        }

        public void bind(Doctor doctor) {
            tvDoctorName.setText(doctor.getDoctorName());
            tvLocation.setText(doctor.getLocation());
            //rbOverallRating.setRating(doctor.getOverallRating());

        }
    }
}
