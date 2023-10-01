package com.example.BootApp.repo;


import com.example.BootApp.DTO.PostHeaderDTO;
import com.example.BootApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepositorry extends JpaRepository<Person,Integer> {


    Optional<Person> findByName(String name);


}
