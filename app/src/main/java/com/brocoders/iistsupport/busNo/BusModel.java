package com.brocoders.iistsupport.busNo;

public class BusModel {
    String busno, driver, contact, locations;

    public BusModel(){

    }
    public BusModel(String busno, String driver, String contact, String locations) {
        this.busno = busno;
        this.driver = driver;
        this.contact = contact;
        this.locations = locations;
    }

    public String getBusno() {
        return busno;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }
}
