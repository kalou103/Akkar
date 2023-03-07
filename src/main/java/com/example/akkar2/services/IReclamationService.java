
package com.example.akkar2.services;

        import com.example.akkar2.entities.Reclamation;

        import java.util.List;

public interface IReclamationService {
    Reclamation addReclamation(Reclamation r);

    List<Reclamation> retrieveAllReclamations();



    void removeReclamation(Long id);

    Reclamation updateReclamation(Reclamation r);

    Reclamation retrieveReclamationById(Long id);
}
