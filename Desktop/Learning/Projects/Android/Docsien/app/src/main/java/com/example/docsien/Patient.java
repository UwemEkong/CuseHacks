package com.example.docsien;

public class Patient {

    public String username, password, medication;

    // Empty constructor for Firebase
    public Patient() {
    }

    public Patient(String username, String password, String medication) {
        this.username = username;
        this.password = password;
        this.medication = medication;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}
