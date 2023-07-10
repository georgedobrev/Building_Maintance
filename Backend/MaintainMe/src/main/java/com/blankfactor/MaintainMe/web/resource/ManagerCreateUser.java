package com.blankfactor.MaintainMe.web.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerCreateUser {

    public RegistrationRequestUser registrationRequestUser;
    public Long buildingID;
    public  Long unitId;
    private Boolean jointStatus = false;
}
