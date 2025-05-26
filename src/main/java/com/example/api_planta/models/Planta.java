package com.example.api_planta.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "plantas")
public class Planta {
    @Id
    private String plantaId = UUID.randomUUID().toString();
    private String description;
}
