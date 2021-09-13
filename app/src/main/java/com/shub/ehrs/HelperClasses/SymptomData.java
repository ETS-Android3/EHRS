package com.shub.ehrs.HelperClasses;

public class SymptomData {
    String symptomName;
    String date;
    String phoneNo;

    public SymptomData(String symptomName, String date, String phoneNo) {
        this.symptomName = symptomName;
        this.date = date;
        this.phoneNo = phoneNo;
    }

    public  SymptomData(){

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
