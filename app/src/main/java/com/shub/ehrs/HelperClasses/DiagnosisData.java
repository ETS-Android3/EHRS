package com.shub.ehrs.HelperClasses;

public class DiagnosisData {
    String diagnosisName;
    String date;



    public DiagnosisData(String diagnosisName, String date) {
        this.diagnosisName = diagnosisName;
        this.date = date;

    }

    public  DiagnosisData(){}

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
