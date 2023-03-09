package com.example.akkar2.repository;


import com.example.akkar2.entities.Client;
import com.example.akkar2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Query(value ="select u.user_type from user  u where u.iduser= :id  ",nativeQuery = true)
    String getDiscriminatorValueById(int id);
    @Transactional
    @Query(value ="select u.firstname  from user  u where u.iduser= :id  ",nativeQuery = true)
    String getfirstnameById(int id);
    @Transactional
    @Query(value ="select u.lastname from user  u where u.iduser= :id  ",nativeQuery = true)
    String getlastnameById(int id);

    //  @Transactional
    //@Query(value ="select * from user u, where u.email= :email  ",nativeQuery = true)
    Client findClientByEmail(String email);
     User findUserById(Long Id);
}