package com.validador.validadorIntento.Models;

public class PersonaExcel {

    private String injuryLocation;
    private String reportType;

    public PersonaExcel(String injuryLocation, String reportType) {
        this.injuryLocation = injuryLocation;
        this.reportType = reportType;
    }

    public String getInjuryLocation() {
        return injuryLocation;
    }

    public String getReportType() {
        return reportType;
    }

}
