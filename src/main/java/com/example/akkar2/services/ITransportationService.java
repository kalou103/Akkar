package com.example.akkar2.services;

import com.example.akkar2.entities.TransportationDemand;

import java.util.List;

public interface ITransportationService {
    public TransportationDemand addTransportationDemand (TransportationDemand transportationD);
    public void deleteTransportationDemand (Long idTransportationD);
    public List<TransportationDemand> GetAllTransportationDemand();
    public TransportationDemand updateTransportationDemand (TransportationDemand transportationD );
}
