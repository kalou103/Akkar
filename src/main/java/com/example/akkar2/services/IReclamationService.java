
package com.example.akkar2.services;

        import com.example.akkar2.entities.Reclamation;

        import java.util.List;

public interface IReclamationService {
    String addReclamation(Reclamation r,Long userid);

    List<Reclamation> retrieveAllReclamations();



    void removeReclamation(Long id);

    Reclamation updateReclamation(Reclamation r);

    Reclamation retrieveReclamationById(Long id);
}
