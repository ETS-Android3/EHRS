package com.shub.ehrs.HelperClasses;

public class NotificationData {
    String fullName;
    String symptomName;
    String date;

    public NotificationData(){}

    public NotificationData(String fullName, String symptomName, String date) {
        this.fullName = fullName;
        this.symptomName = symptomName;
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
