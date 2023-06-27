package com.blankfactor.MaintainMe.web.resource;

import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserInfoResponse {

    private User user;

    private List<Unit> units;



}
