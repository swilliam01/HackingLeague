package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 3)
    private String firstName;

    @NotNull
    @Size(min = 3)
    private String lastName;

    @NotNull
    @Size(min=4)
    private String email;

    @NotNull
    @Size(min = 3)
    private String company;

    @NotNull
    @Size(min = 3)
    private String title;


    @ManyToMany(mappedBy = "sponsors", fetch = FetchType.LAZY)
    private Collection<Hackathon> hackathons;

    public Sponsor() {
    }

    public Sponsor(@NotNull @Size(min = 3) String firstName, @NotNull @Size(min = 3) String lastName, @NotNull @Size(min = 4) String email, @NotNull @Size(min = 3) String company, @NotNull @Size(min = 3) String title, Collection<Hackathon> hackathons) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.title = title;
        this.hackathons = hackathons;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Hackathon> getHackathons() {
        return hackathons;
    }

    public void setHackathons(Collection<Hackathon> hackathons) {
        this.hackathons = hackathons;
    }
}
