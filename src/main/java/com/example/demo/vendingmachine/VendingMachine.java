package com.example.demo.vendingmachine;

import com.example.demo.appuser.AppUser;
import jakarta.persistence.*;

@Entity
public class VendingMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser owner;

    public VendingMachine() {
    }

    public VendingMachine(String name, String location, int capacity, AppUser owner) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }
}
