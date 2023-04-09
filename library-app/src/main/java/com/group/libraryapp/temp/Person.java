package com.group.libraryapp.temp;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
        this.address.setPerson(this);
    }
}
