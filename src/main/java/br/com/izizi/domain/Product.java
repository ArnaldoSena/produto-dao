package br.com.izizi.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product extends PanacheEntityBase { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    public long Id;
    
    public String name;

    public String value;

}
