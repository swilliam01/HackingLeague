package com.example.demo.Repositories;

import com.example.demo.models.Sponsor;
import org.springframework.data.repository.CrudRepository;

public interface SponsorRepo extends CrudRepository<Sponsor, Long> {
//    String sponsor findBy
}
