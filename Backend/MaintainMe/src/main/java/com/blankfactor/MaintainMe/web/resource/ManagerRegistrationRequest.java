package com.blankfactor.MaintainMe.web.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerRegistrationRequest {

    BuildingResource buildingResource;
    RegistrationRequestManager registrationRequestManager;
}
