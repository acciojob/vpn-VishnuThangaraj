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

        country.setUser(user);
        user.setOriginalCountry(country);
        user.setConnected(false);

        user.setOriginalIp(country.getCode()+"."+userRepository3.save(user).getId());

        userRepository3.save(user);
        return user;
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {
        User user = userRepository3.findById(userId).get();
        ServiceProvider serviceProvider = serviceProviderRepository3.findById(serviceProviderId).orElse(null);

        user.getServiceProviderList().add(serviceProvider);
        serviceProvider.getUsers().add(user);

        serviceProviderRepository3.save(serviceProvider);

        return user;
    }
}
