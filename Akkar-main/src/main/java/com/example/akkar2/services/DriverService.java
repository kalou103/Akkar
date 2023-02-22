package com.example.akkar2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.akkar2.entities.Driver;
import com.example.akkar2.repository.DriverRepository;

import java.util.List;
@Service
public class DriverService implements IDriverService{
    @Autowired
    DriverRepository DriverRepository;
    @Override
    public Driver addDriver(Driver d) {
        return DriverRepository.save(d);
    }

    @Override
    public List<Driver> retrieveAllDriver() {
        return (List<Driver>) DriverRepository.findAll();
    }

    @Override
    public Driver retrieveDriver(int id) {
        return DriverRepository.findById(id).orElse(new Driver());
    }

    @Override
    public void removeDriver(int id) {
        DriverRepository.deleteById(id);
    }

    @Override
    public Driver updateDriver(Driver d) {
        return DriverRepository.save(d);
    }


}
