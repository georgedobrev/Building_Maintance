package com.blankfactor.MaintainMe.web.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ManagerRegistrationRequest {

    BuildingResource buildingResource;
    RegistrationRequest registrationRequest;
}
