package com.example.akkar2.services;

import com.example.akkar2.entities.ExpertAppointment;

import java.util.List;

public interface IExpertAppointmentService {
    public ExpertAppointment AddExpertAppointment(ExpertAppointment expert);
    public List<ExpertAppointment> ShowAllExpertAppointments();
    public void DeleteExpertAppointment(Long id);
    public ExpertAppointment updateExpertAppointment( ExpertAppointment e);

}
