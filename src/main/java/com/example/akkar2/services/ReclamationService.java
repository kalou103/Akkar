
package com.example.akkar2.services;

        import com.example.akkar2.entities.Reclamation;
        import com.example.akkar2.repository.ReclamationRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class ReclamationService implements IReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Override
    public Reclamation addReclamation(Reclamation r) {
        return reclamationRepository.save(r);
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
