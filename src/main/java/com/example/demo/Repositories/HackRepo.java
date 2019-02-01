package com.example.demo.Repositories;

import com.example.demo.models.Hackathon;
import org.springframework.data.repository.CrudRepository;

public interface HackRepo extends CrudRepository<Hackathon, Long> {
}
