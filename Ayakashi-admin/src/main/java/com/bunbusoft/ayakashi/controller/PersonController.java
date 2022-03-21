package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.domain.Person;
import com.bunbusoft.ayakashi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "persons")
    public List<Person> findAll() {
        personService.addPerson();
        return personService.getAllPersons();
    }

}
