package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetOrganisationRequest {
    private String orgId;
    private String userId;
}
