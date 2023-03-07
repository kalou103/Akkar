package com.example.akkar2.services;


import java.util.List;

import com.example.akkar2.entities.Feedback;


public interface Ifeedback {

    List<Feedback> getAllFeedBacks();
    String AddFeedback(Feedback feedback, int userid);
    void deleteFeedback(int id);
}