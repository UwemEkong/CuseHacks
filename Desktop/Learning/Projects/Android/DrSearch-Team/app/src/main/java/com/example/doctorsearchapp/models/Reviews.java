package com.example.doctorsearchapp.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Review")
public class Reviews extends ParseObject {

    public static final String KEY_REVIEW = "Review";
    public static final String KEY_USER = "userId";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_DOCTOR = "doctorId";

    public String getReview()
    {
        return getString(KEY_REVIEW);
    }

    public void setReview(String review)
    {
        put(KEY_REVIEW, review);
    }

    public void setUser(ParseUser user)
    {
        put(KEY_USER,user);
    }

    public ParseUser getUser()
    {
        return getParseUser(KEY_USER);
    }
}

