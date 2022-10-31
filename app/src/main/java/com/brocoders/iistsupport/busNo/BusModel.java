package com.brocoders.iistsupport.busNo;

public class BusModel {
    String busNo, driver, contact;

    public BusModel(String busNo, String driver, String contact) {
        this.busNo = busNo;
        this.driver = driver;
        this.contact = contact;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
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
}
