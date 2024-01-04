package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String password;
    private List<ServiceProvider> serviceProviders;

    // No-Args Constructor

    public Admin() {
        serviceProviders = new ArrayList<>();
    }

    // All-Args Constructor

    public Admin(int id, String username, String password, List<ServiceProvider> serviceProviders) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.serviceProviders = serviceProviders;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }
}