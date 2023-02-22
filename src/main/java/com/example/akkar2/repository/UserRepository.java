package com.example.akkar2.repository;

import com.example.akkar2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM User s " +
            "WHERE s.email = ?1"
    )
    Boolean selectExistsUserEmail(String userEmail);
User findUserById(Long uid);


}


