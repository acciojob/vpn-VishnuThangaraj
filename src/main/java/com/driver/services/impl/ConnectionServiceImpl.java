package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Override
    public User connect(int userId, String countryName) throws Exception{
        User user = userRepository2.findById(userId).orElse(null);

        assert user != null;
        if(user.isConnected()) throw new Exception("Already connected");

        Country country = user.getOriginalCountry();
        countryName = countryName.toUpperCase();

        if(countryName.equals(country.getCountryName().toString()))
            return user;

        List<ServiceProvider> serviceProviders = user.getServiceProviderList();
        if(serviceProviders.isEmpty())
            throw new Exception("serviceProviders");

        ServiceProvider serviceProvider = new ServiceProvider();

//        for(ServiceProvider provider : serviceProviders){
//            for(Country country1 : )
//        }

        return user;
    }
    @Override
    public User disconnect(int userId) throws Exception {
        User user = userRepository2.findById(userId).orElse(null);

        assert user != null;
        if(!user.isConnected()) throw new Exception("Already disconnected");

        user.setConnected(false);
        user.setMaskedIp(null);

        return  user;
    }
    @Override
    public User communicate(int senderId, int receiverId) throws Exception {
        User sender = userRepository2.findById(senderId).orElse(null);
        User receiver = userRepository2.findById(receiverId).orElse(null);

        Country senderCountry = sender.getOriginalCountry();
        Country receiverCountry = receiver.getOriginalCountry();

        return sender;
    }
}
