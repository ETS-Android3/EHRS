package com.shub.ehrs.HelperClasses;

public class ReportData {
    String reportName;
    String date;
    String reportUri;

    public ReportData(String reportName, String date, String reportUri) {
        this.reportName = reportName;
        this.date = date;
        this.reportUri = reportUri;
    }

    public  ReportData(){}

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReportUri() {
        return reportUri;
    }

    public void setReportUri(String reportUri) {
        this.reportUri = reportUri;
    }
}
