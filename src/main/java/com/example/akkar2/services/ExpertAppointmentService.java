package com.example.akkar2.services;

import com.example.akkar2.entities.Expert;
import com.example.akkar2.entities.ExpertAnalysis;
import com.example.akkar2.entities.ExpertAppointment;
import com.example.akkar2.repository.IExpertAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertAppointmentService implements IExpertAppointmentService{
    @Autowired
    IExpertAppointmentRepository iExpertAppointmentRepository;


    @Override
    public ExpertAppointment AddExpertAppointment(ExpertAppointment expert) {
        return iExpertAppointmentRepository.save(expert);
    }

    @Override
    public List<ExpertAppointment> ShowAllExpertAppointments() {
        return iExpertAppointmentRepository.findAll();
    }

    @Override
    public void DeleteExpertAppointment(Long id) {
        iExpertAppointmentRepository.deleteById(id);
    }

    @Override
    public ExpertAppointment updateExpertAppointment( ExpertAppointment e) {

        return iExpertAppointmentRepository.save(e);

    }
}
