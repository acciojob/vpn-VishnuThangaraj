package com.driver.services.impl;

import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        adminRepository1.save(admin);

        return admin;
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin = adminRepository1.findById(adminId).get();
        ServiceProvider serviceProvider = new ServiceProvider();

        serviceProvider.setName(providerName);
        serviceProvider.setAdmin(admin);

        admin.getServiceProviders().add(serviceProvider);
        adminRepository1.save(admin);

        return admin;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{

        //ind, usa, aus, , chi, jpn (00X)
        countryName = countryName.toUpperCase();

        Country country = new Country();
        switch (countryName){
            case ("IND"):
                country.setCountryName(CountryName.IND);
                country.setCode(CountryName.IND.toCode());
                break;
            case ("USA"):
                country.setCountryName(CountryName.USA);
                country.setCode(CountryName.USA.toCode());
                break;
            case ("AUS"):
                country.setCountryName(CountryName.AUS);
                country.setCode(CountryName.AUS.toCode());
                break;
            case ("CHI"):
                country.setCountryName(CountryName.CHI);
                country.setCode(CountryName.CHI.toCode());
                break;
            case ("JPN"):
                country.setCountryName(CountryName.JPN);
                country.setCode(CountryName.JPN.toCode());
                break;
            default:
                throw new Exception("Country not found");
        }
        ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).orElse(null);

        country.setServiceProvider(serviceProvider);
        serviceProvider.getCountryList().add(country);
        serviceProviderRepository1.save(serviceProvider);

        return serviceProvider;
    }
}
