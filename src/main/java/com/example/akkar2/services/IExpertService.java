package com.example.akkar2.services;

import com.example.akkar2.entities.Expert;

import java.util.List;

public interface IExpertService {
    Expert addUser(Expert expert);
    List<Expert> retrieveAllExpert();
    Expert retrieveExpert(int id);
     void removeExepert(int id);
    Expert updateExpert(Expert e);
}
