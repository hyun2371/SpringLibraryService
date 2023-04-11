package com.group.libraryapp.temp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    @Transactional
    public void savePerson(){
        Person person = personRepository.save(new Person());
        Address address = addressRepository.save(new Address());
        person.setAddress(address);
    }
}
