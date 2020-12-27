package com.card.consumer.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String fname;
    private String lname;
    @OneToMany
    @JoinColumn(name = "ID")
    private Card cardEntity;

}
