package com.example.doctorsearchapp.models;

import com.parse.ParseFile;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;

public class Users {

    ParseUser user;
    private static final String KEY_IMAGE = "profilePicture";

    public Users(ParseUser user)
    {
        this.user = user;
    }

    public String getUsername()
    {
        return user.getUsername();
    }

    public ParseFile getProfilePic()
    {
        return user.getParseFile(KEY_IMAGE);
    }

    public String getProfileCreation()
    {
        return new SimpleDateFormat("MMMM dd, yyyy").format(user.getCreatedAt());
    }

}