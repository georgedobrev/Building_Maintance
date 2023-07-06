package com.blankfactor.MaintainMe.web.resource;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationRequest {
   private Boolean jointStatus = false;
   public Long buildingId;
   public Long userID;
   public Long unitId;

}
