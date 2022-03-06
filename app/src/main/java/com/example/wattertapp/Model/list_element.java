package com.example.wattertapp.Model;

import java.io.Serializable;

public class list_element implements Serializable {

    public String colo;
    public String name;
    public String city;
    public String status;

    public  list_element(){

    }

    public list_element(String colo, String name, String city, String status) {
        this.colo = colo;
        this.name = name;
        this.city = city;
        this.status = status;
    }

    public String getColo() {
        return colo;
    }

    public void setColo(String colo) {
        this.colo = colo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}