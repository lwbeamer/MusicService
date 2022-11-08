package com.example.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.server.entity.Subscription} entity
 */
@Data
public class SubscriptionDTO implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final Long price;
}