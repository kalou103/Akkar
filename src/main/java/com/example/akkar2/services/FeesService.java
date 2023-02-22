package com.example.akkar2.services;

import com.example.akkar2.entities.RealEstatesFees;
import com.example.akkar2.repository.RealEstateFeesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeesService  implements IFeesService {

    RealEstateFeesRepository feesRepo;
        @Override
        public RealEstatesFees addFees(RealEstatesFees p) {


            return feesRepo.save(p) ;
        }

        @Override
        public List<RealEstatesFees> retrieveAllFees() {
            return feesRepo.findAll();
        }



        @Override
        public void removeFees(Long id) {
            feesRepo.deleteById(id);
        }



        @Override
        public RealEstatesFees updateFees(RealEstatesFees p) {
            return feesRepo.save(p);
        }

    }
