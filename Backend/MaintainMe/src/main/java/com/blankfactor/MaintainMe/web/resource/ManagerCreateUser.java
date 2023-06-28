package com.blankfactor.MaintainMe.web.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerCreateUser {

    RegistrationRequestUser registrationRequestUser;
    Long buildingID;
    Long unitId;
}
