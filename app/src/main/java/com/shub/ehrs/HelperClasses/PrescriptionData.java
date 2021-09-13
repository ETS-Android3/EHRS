package com.shub.ehrs.HelperClasses;

public class PrescriptionData {
    String prescriptionName;
    String date;
    String prescriptionDesc;

    public PrescriptionData(String prescriptionName, String date, String prescriptionDesc) {
        this.prescriptionName = prescriptionName;
        this.date = date;
        this.prescriptionDesc = prescriptionDesc;
    }

    public PrescriptionData() {

    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrescriptionDesc() {
        return prescriptionDesc;
    }

    public void setPrescriptionDesc(String prescriptionDesc) {
        this.prescriptionDesc = prescriptionDesc;
    }
}
