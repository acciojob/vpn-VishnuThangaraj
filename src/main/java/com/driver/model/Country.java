package com.driver.model;
// Note: Do not write @Enumerated annotation above CountryName in this model.
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private CountryName countryName;
    private String codes;
    private User user;
    private ServiceProvider serviceProvider;

    // No-Args Constructor

    public Country() {
    }

    // All-Args Constructor

    public Country(int id, CountryName countryName, String codes, User user, ServiceProvider serviceProvider) {
        this.id = id;
        this.countryName = countryName;
        this.codes = codes;
        this.user = user;
        this.serviceProvider = serviceProvider;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
