package com.example.doctorsearchapp.models;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.parceler.Parcel;

import java.util.ArrayList;

@ParseClassName("Doctor")
@Parcel(analyze = Doctor.class)
public class Doctor extends ParseObject implements Parcelable {

    public static final String KEY_DOCTOR_NAME = "doctorName";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_REVIEW_LIST = "reviewList";
//    public static final String KEY_RATING = "overallRating";

    // Empty constructor for Parceler library
    public Doctor() { }

    public String getDoctorName() {
        return getString(KEY_DOCTOR_NAME);
    }

    public void setDoctorName(String doctorName) {
        put(KEY_DOCTOR_NAME, doctorName);
    }

    public String getLocation() {
        return getString(KEY_LOCATION);
    }

    public ArrayList<String> getReviews()
    {
        return (ArrayList<String>) get(KEY_REVIEW_LIST);
    }

    public void saveReview(String reviewId)
    {
        ArrayList<String> reviews = getReviews();
        reviews.add(reviewId);
        put(KEY_REVIEW_LIST, reviews);
        saveInBackground();
    }

    public void setLocation(String location) {
        put(KEY_LOCATION, location);
    }

//    public int doctorId;
//    public String doctorName;
//    public float overallRating;
//    public String location;
//    public ArrayList<Integer> reviewList;

//    public Doctor() {};
//
//    public Doctor(int doctorId, String doctorName, int overallRating, String location, ArrayList<Integer> reviewList) {
//        this.doctorId = doctorId;
//        this.doctorName = doctorName;
//        this.overallRating = overallRating;
//        this.location = location;
//        this.reviewList = reviewList;
//    }
//
//    public int getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(int doctorId) {
//        this.doctorId = doctorId;
//    }
//
//    public String getDoctorName() {
//        return doctorName;
//    }
//
//    public void setDoctorName(String doctorName) {
//        this.doctorName = doctorName;
//    }
//
//    public float getOverallRating() {
//        return overallRating;
//    }
//
//    public void setOverallRating(int overallRating) {
//        this.overallRating = overallRating;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public ArrayList<Integer> getReviewList() {
//        return reviewList;
//    }
//
//    public void setReviewList(ArrayList<Integer> reviewList) {
//        this.reviewList = reviewList;
//    }
}
