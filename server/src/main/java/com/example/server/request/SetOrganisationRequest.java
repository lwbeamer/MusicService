package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetOrganisationRequest {
    private Long orgId;
    private Long userId;
}
