package com.example.akkar2.controllers;

import com.example.akkar2.entities.Expert;
import com.example.akkar2.entities.ExpertAnalysis;
import com.example.akkar2.entities.ExpertAppointment;
import com.example.akkar2.services.ExpertAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ExpertAppointment")
public class ExpertAppointmentController {
    @Autowired
    ExpertAppointmentService expertAppointmentService;
    @PostMapping
    public ExpertAppointment addExpertAppointement(@RequestBody ExpertAppointment expertAppointment) {
        return expertAppointmentService.AddExpertAppointment(expertAppointment);
    }
    @GetMapping
    public List<ExpertAppointment> getAllExpertAppointments() {
        return expertAppointmentService.ShowAllExpertAppointments();
    }
    @DeleteMapping("/{expertAppointmentId}")
    public void DeleteExpert(@PathVariable long expertId)
    {
        expertAppointmentService.DeleteExpertAppointment(expertId);
    }
    @PutMapping("/updateExpertAppointment")
    @ResponseBody
    public ExpertAppointment updateExpertAppointment(@RequestBody ExpertAppointment c) {
        return expertAppointmentService.updateExpertAppointment(c);

    }
}
