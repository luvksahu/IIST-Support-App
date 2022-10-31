package com.brocoders.iistsupport.route;

public class RouteModel {
    String location, time, sign;

    public RouteModel(String location, String time, String sign) {
        this.location = location;
        this.time = time;
        this.sign = sign;
    }

    public RouteModel() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
