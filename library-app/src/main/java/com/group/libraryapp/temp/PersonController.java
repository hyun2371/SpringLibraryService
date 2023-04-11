package com.group.libraryapp.temp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    public void savePerson(){
        personService.savePerson();
    }
}
