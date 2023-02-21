package com.example.akkar2.services;

import com.example.akkar2.entities.Papers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPapersService {

    Papers addLoanPics(Long ID, List<MultipartFile> images) throws IOException;
   Papers addPapers(Papers p);
  /*  List<Papers> retrieveAllPapers();
    void removePapers(Long id);
    Papers updatePapers(Papers p);
*/
}
