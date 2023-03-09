package com.example.akkar2.services;


import com.example.akkar2.entities.TransportationDemand;
import com.example.akkar2.entities.TransportationType;
import com.example.akkar2.repository.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportationService implements ITransportationService{
    @Autowired
    TransportationRepository transportationRepository;

    @Override
    public TransportationDemand addTransportationDemand(TransportationDemand transportationD)
    {
        return transportationRepository.save(transportationD);
    }

    @Override
    public void deleteTransportationDemand(Long idTransportationD)
    {
        transportationRepository.deleteById(idTransportationD);
    }

    @Override
    public List<TransportationDemand> GetAllTransportationDemand()
    {
        return transportationRepository.findAll();
    }

    @Override
    public TransportationDemand updateTransportationDemand(TransportationDemand transportationD)
    {

        return transportationRepository.save(transportationD);
    }




}
