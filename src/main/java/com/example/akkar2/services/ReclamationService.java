
package com.example.akkar2.services;

        import com.example.akkar2.entities.Client;
        import com.example.akkar2.entities.Reclamation;
        import com.example.akkar2.entities.User;
        import com.example.akkar2.repository.ReclamationRepository;
        import com.example.akkar2.repository.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.Date;
        import java.util.List;

@Service
public class ReclamationService implements IReclamationService {
    @Autowired
    UserRepository userRepo ;
    @Autowired
    ReclamationRepository reclamationRepository;

    @Override
    public String addReclamation(Reclamation r,Long userid) {
        String x =null;
      User user =userRepo.findUserById(userid);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:\\Users\\bensa\\OneDrive\\Bureau\\dorra\\Pidev\\Akkar\\src\\main\\resources\\badWords.txt"));
            String line = reader.readLine();
            if (line.contains(r.getContent()))
            {
                System.out.println("You are using bad words");
                //verif = true ;
                x= "You are using bad words";
            }
            else {
              //  r.setDateFeedback(new Date());
                r.setClient((Client) user);
                reclamationRepository.save(r);

                x= "save" +r;
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return x;
    }

    @Override
    public List<Reclamation> retrieveAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation retrieveReclamationById(Long id) {
        return reclamationRepository.findById(id).get();
    }

    @Override
    public void removeReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }

    @Override
    public Reclamation updateReclamation(Reclamation r) {
        return reclamationRepository.save(r);
    }


}
