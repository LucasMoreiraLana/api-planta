package com.example.api_planta.services;

import com.example.api_planta.dtos.request.RequestCreatePlanta;
import com.example.api_planta.dtos.response.ResponseCreatePlanta;
import com.example.api_planta.models.Planta;
import com.example.api_planta.repositories.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePlantaService {

    @Autowired
    private PlantaRepository plantaRepository;

    public ResponseCreatePlanta createPlanta(RequestCreatePlanta requestCreatePlanta){
        Planta planta = new Planta();
        planta.setDescription(requestCreatePlanta.description());

        Planta created = plantaRepository.save(planta);

        return new ResponseCreatePlanta(created.getPlantaId(), created.getDescription());
    }

}
