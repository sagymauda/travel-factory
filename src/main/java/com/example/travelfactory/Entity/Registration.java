package com.example.travelfactory.Entity;


import lombok.Data;

import javax.persistence.Id;

import javax.persistence.*;

@Entity
@Data
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", unique = true )
    private String name;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "email")

    private String email;
    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;


}
