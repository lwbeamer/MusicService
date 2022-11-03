package com.example.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.server.entity.Genre} entity
 */
@Data
public class GenreDTO implements Serializable {
    private final Long id;
    private final String name;
}