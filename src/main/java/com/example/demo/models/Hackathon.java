package com.example.demo.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "Hack_Sponsor")
public class Hackathon {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @NotNull
        @Size(min = 3)
        private String name;

        @NotNull
        @Size(min = 3)
        private String startDate;

        @NotNull
        @Size(min=4)
        private String endDate;

        @NotNull
        @Size(min = 3)
        private String city;

        @NotNull
        @Size(min = 2 )
        private String state;

        @NotNull
        @Size(min = 2)
        private String webLink;

        private String headshot;


    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(joinColumns = @JoinColumn(name = "hackathon_id"), inverseJoinColumns = @JoinColumn(name = "sponsor_id"))
        private Collection<Sponsor> sponsors;

    public Hackathon() {
    }

    public Hackathon(@NotNull @Size(min = 3) String name, @NotNull @Size(min = 3) String startDate, @NotNull @Size(min = 4) String endDate, @NotNull @Size(min = 3) String city, @NotNull @Size(min = 2) String state, @NotNull @Size(min = 2)
            String webLink, String headshot, Collection<Sponsor> sponsors) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.state = state;
        this.webLink = webLink;
        this.headshot = headshot;
        this.sponsors = sponsors;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot)
    {
        this.headshot = headshot;
    }

    public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getStartDate() {
                return startDate;
        }

        public void setStartDate(String startDate) {
                this.startDate = startDate;
        }

        public String getEndDate() {
                return endDate;
        }

        public void setEndDate(String endDate) {
                this.endDate = endDate;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public Collection<Sponsor> getSponsors() {
                return sponsors;
        }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public void setSponsors(Collection<Sponsor> sponsors) {
                this.sponsors = sponsors;
        }
}

