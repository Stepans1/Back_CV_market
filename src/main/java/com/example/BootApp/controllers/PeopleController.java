package com.example.BootApp.controllers;

import com.example.BootApp.DTO.SetOwnerDTO;
import com.example.BootApp.dao.PersonDAO;
import com.example.BootApp.models.Person;
import com.example.BootApp.models.WorkType;
import com.example.BootApp.services.PeopleService;
import com.example.BootApp.util.PersonValidator;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("people")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST}, allowedHeaders = "*")
public class PeopleController {




    private final PeopleService peopleService;
    private final PersonValidator personValidator ;
    @Autowired
    public PeopleController(PersonDAO personDAO, PeopleService peopleService,PersonValidator personValidator){
        this.peopleService = peopleService;

        this.personValidator = personValidator;
    }





    @GetMapping("")
    public String index( Model model) throws SQLException {

        model.addAttribute("people",peopleService.findAll());

        return "people/index";
    }

    @GetMapping("/getUsersByName")
    @ResponseBody
    public List<SetOwnerDTO> getUserByName(@RequestParam String name){
      return peopleService.getByName(name);
    }



    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model) throws SQLException {

        model.addAttribute("person",peopleService.findOne(id));

        return "people/show";
    }


    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person",new Person());

        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person")@Valid Person person,
                         BindingResult bindingResult) throws SQLException {

        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id) throws SQLException {
        model.addAttribute("person",peopleService.findOne(id));
        return "people/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person ,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) throws SQLException {

        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()){
            return "people/edit";
        }
        peopleService.update(id,person);
        return "redirect:/people";
    }

    @PostMapping("/del/{id}")
    public String del(@PathVariable("id") int id) throws SQLException {
        peopleService.del(id);
        return "redirect:/people";
    }
}
