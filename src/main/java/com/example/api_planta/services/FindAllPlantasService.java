package com.example.api_planta.services;

import com.example.api_planta.dtos.response.ResponseFindPlanta;
import com.example.api_planta.models.Planta;
import com.example.api_planta.repositories.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllPlantasService {

    @Autowired
    PlantaRepository plantaRepository;

    public List<ResponseFindPlanta> findAll() {
        List<Planta> plantas = plantaRepository.findAll();

        return plantas.stream()
                .map(planta -> new ResponseFindPlanta(planta.getPlantaId(), planta.getDescription()))
                .collect(Collectors.toList());
    }
}
