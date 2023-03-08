package com.example.akkar2.services;

import com.example.akkar2.entities.Expert;


import java.util.List;

public interface IExpertService {
    public Expert AddExpert(Expert expert);
    public List<Expert> ShowAllExperts();
    public void DeleteExpertByCin(Long cin);
    public Expert updateExpert(Expert expert);
    public void DeleteExpertById(int id);
    public List<Expert> findAllExpertByLoaction(String Location);



}
