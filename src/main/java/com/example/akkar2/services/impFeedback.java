package com.example.akkar2.services;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
        import java.io.IOException;
        import java.util.Date;
        import java.util.List;

import com.example.akkar2.entities.Feedback;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.FeedbackRepo;
import com.example.akkar2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

     //   import net.bytebuddy.asm.Advice.Exit;

      //  import tn.esprit.spring.entites.User;

      //  import tn.esprit.spring.repositories.UserRepo;

@Service
public class impFeedback implements Ifeedback{

    @Autowired
    FeedbackRepo feedbackRepo ;
    @Autowired
    UserRepository userRepo ;
    @Override
    public String AddFeedback(Feedback feedback, int userid) {
        String x =null;
      User user = userRepo.findById(userid);
        boolean verif = false ;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:\\Users\\dorra\\Desktop\\Pidev\\Akkar\\src\\main\\resources\\badWords.txt"));
            String line = reader.readLine();
                if (line.contains(feedback.getFeedbackbody()))
                {
                    System.out.println("You are using bad words");
                    //verif = true ;
                    x= "erreur";
                }
                else {
                    feedback.setDateFeedback(new Date());
                    feedback.setUser(user);
                    feedbackRepo.save(feedback);
                    x= "save";
                }
                // read next line

            } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return x;
    }

    @Override
    public void deleteFeedback(int id) {
        // TODO Auto-generated method stub
        this.feedbackRepo.deleteById(id);
    }

    @Override
    public List<Feedback> getAllFeedBacks() {
        // TODO Auto-generated method stub
        return feedbackRepo.findAll();
    }

}