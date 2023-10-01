package com.example.BootApp.services;



import com.example.BootApp.models.Person;

import com.example.BootApp.repo.PeopleRepositorry;
import com.example.BootApp.util.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepositorry peopleRepositorry;

    @Autowired
    public PeopleService(PeopleRepositorry peopleRepositorry) {
        this.peopleRepositorry = peopleRepositorry;

    }


    public List<Person> findAll(){
        return peopleRepositorry.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson=peopleRepositorry.findById(id);

        return foundPerson.orElseThrow(PostNotFoundException::new);
    }

    @Transactional
    public void save(Person person){
        peopleRepositorry.save(person);

    }

    @Transactional
    public void update(int id,Person updatesPeron){
        updatesPeron.setId(id);
        peopleRepositorry.save(updatesPeron);
    }


    @Transactional
    public void del(int id){
        peopleRepositorry.deleteById(id);
    }





}
