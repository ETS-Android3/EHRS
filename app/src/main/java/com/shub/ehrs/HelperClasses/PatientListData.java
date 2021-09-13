package com.shub.ehrs.HelperClasses;

public class PatientListData {
    String fullName, username, email, phoneNo, password, date, gender, bloodGroup, weight;

    public PatientListData(String fullName, String username, String email, String phoneNo, String password, String date, String gender, String bloodGroup, String weight) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.date = date;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.weight = weight;
    }

    public PatientListData(){ }


    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNo() { return phoneNo; }

    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getBloodGroup() { return bloodGroup; }

    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getWeight() { return weight; }

    public void setWeight(String weight) { this.weight = weight; }
}
