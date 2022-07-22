package com.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable   //value type must be associated with owning entity nd doesnt have sep entity/existence...
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //based on name & age both if two objects are equal then they produce same hashcode...
@ToString

//we are using hibernate internally as a jpa implementator...
public class AuthorId implements Serializable {

    private static final long serialVersionUID = 1L; 
    @Column(length = 30)
    private String name;   
    private int age;

 }
