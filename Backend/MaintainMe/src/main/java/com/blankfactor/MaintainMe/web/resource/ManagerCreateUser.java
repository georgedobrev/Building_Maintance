package com.blankfactor.MaintainMe.web.resource;

import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerCreateUser {

    RegistrationRequest registrationRequest;
    Building building;
    Unit unit;
}
