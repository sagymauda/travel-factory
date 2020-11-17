package com.example.travelfactory.Entity;



import lombok.Data;

import javax.persistence.Id;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "campaign_name")
    private String campaignName;

    private String mandatoryFields;

    @OneToMany(cascade=CascadeType.ALL,  mappedBy = "campaign")
    private List<Registration>registrations;
}
