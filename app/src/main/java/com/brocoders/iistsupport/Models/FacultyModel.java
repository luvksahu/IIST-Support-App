package com.brocoders.iistsupport.Models;


public class FacultyModel {
    String email,expertise,name,role,furl;

    public FacultyModel(String email, String expertise, String name, String role, String furl){
        this.email=email;
        this.expertise=expertise;
        this.name=name;
        this.role=role;
        this.furl=furl;
    }
    public FacultyModel(){
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpertise() {
        return expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getFurl() {
        return furl;
    }
    public void setFurl(String furl) {
        this.furl = furl;
    }
}
