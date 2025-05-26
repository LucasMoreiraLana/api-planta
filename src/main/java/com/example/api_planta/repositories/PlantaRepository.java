package com.example.api_planta.repositories;

import com.example.api_planta.models.Planta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlantaRepository extends MongoRepository<Planta, String> {

}
