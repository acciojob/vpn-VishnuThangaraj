package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{
        User user = new User();
        countryName = countryName.toUpperCase();
        user.setUsername(username);
        user.setPassword(password);

        Country country = new Country();
        switch (countryName){
            case ("IND"):
                country.setCountryName(CountryName.IND);
                country.setCodes("001");
                break;
            case ("USA"):
                country.setCountryName(CountryName.USA);
                country.setCodes("002");
                break;
            case ("AUS"):
                country.setCountryName(CountryName.AUS);
                country.setCodes("003");
                break;
            case ("CHI"):
                country.setCountryName(CountryName.CHI);
                country.setCodes("004");
                break;
            case ("JPN"):
                country.setCountryName(CountryName.JPN);
                country.setCodes("005");
                break;
            default:
                throw new Exception("Country not found");
        }
        user.setOriginalIp(country.getCodes()+"."+user.getId());
        user.setCountry(country);
        user.setConnected(false);
        user.setMaskedIp(null);

        countryRepository3.save(country);
        userRepository3.save(user);
        return user;
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {
        User user = userRepository3.findById(userId).orElse(null);
        ServiceProvider serviceProvider = serviceProviderRepository3.findById(serviceProviderId).orElse(null);

        serviceProvider.getUsers().add(user);
        user.getServiceProviderList().add(serviceProvider);

        return user;
    }
}
