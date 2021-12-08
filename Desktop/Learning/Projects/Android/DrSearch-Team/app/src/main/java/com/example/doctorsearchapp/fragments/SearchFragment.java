package com.example.doctorsearchapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.doctorsearchapp.MainActivity;
import com.example.doctorsearchapp.R;
import com.example.doctorsearchapp.adapters.DoctorAdapter;
import com.example.doctorsearchapp.models.Doctor;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";
    private SearchView svDoctors;
    private RecyclerView rvSearchResults;
    protected DoctorAdapter adapter;
    protected List<Doctor> allDoctors;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        svDoctors = view.findViewById(R.id.svSearchDoctors);
        rvSearchResults = view.findViewById(R.id.rvSearchResults);

        // Create data source and adapter
        allDoctors = new ArrayList<>();
        adapter = new DoctorAdapter(getContext(), allDoctors);

        // Set adapter on the recycler view
        rvSearchResults.setAdapter(adapter);

        // Set layout manager on recycler view
        rvSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));

        queryDoctors();
    }

    protected void queryDoctors() {
        ParseQuery<Doctor> query = ParseQuery.getQuery(Doctor.class);

        // Get all doctors
        query.findInBackground(new FindCallback<Doctor>() {
            @Override
            public void done(List<Doctor> doctors, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting doctors", e);
                    return;
                }
                for (Doctor doctor : doctors) {
                    Log.i(TAG, "Doctor: " + doctor.getDoctorName() + ", " + doctor.getLocation());
                }
                allDoctors.addAll(doctors);
                adapter.notifyDataSetChanged();
            }
        });
    }
}