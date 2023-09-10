package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name= "batteries")
public class Batteries {
    @Id
    @GeneratedValue//This is for generate the id automatically by jpa
    private int id;

    @Column(name = "name")//adding this above every attribute will rename the column name
    private String name;

    @Column(name = "postCode")
    private long postCode;

    @Column(name = "capacity")
    private int capacity;

    @Column(name="capacityType")
    private String capacityType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPostcode() {
        return postCode;
    }

    public void setPostCode(long postCode) {
        this.postCode = postCode;
    }

    public int getCapacity(){
        return this.capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCapacityType() {
        return capacityType;
    }

    public void setCapacityType(String capacityType) {
        this.capacityType = capacityType;
    }

//    name
//    postcode
//    capacity
//    capacity type
}
