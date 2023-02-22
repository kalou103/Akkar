package com.example.akkar2.services;

import com.example.akkar2.entities.Driver;

import java.util.List;

public interface IDriverService {
    Driver addDriver(Driver c);

    List<Driver> retrieveAllDriver();
    Driver retrieveDriver(int id);

    void  removeDriver(int id);
    Driver updateDriver(Driver d);
}
