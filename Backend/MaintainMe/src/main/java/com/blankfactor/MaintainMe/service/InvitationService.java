package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Invitation;
import com.blankfactor.MaintainMe.repository.BuildingRepository;
import com.blankfactor.MaintainMe.repository.InvitationRepository;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.repository.UnitRepository;
import com.blankfactor.MaintainMe.web.resource.InvitationRequest;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {

   private final InvitationRepository invitationRepository;
   private final UnitRepository unitRepository;
   private final LocalUserRepository userRepository;
   private final BuildingRepository buildingRepository;



    public InvitationService(InvitationRepository invitationRepository, UnitRepository unitRepository, LocalUserRepository userRepository, BuildingRepository buildingRepository) {
        this.invitationRepository = invitationRepository;
        this.unitRepository = unitRepository;
        this.userRepository = userRepository;
        this.buildingRepository = buildingRepository;
    }

    public void sendInvitation(InvitationRequest invitationRequest) throws Exception {
       Invitation invitation = new Invitation();

       invitation.setUnit(unitRepository.findById(invitationRequest.getUnitId())
               .orElseThrow(null));
       invitation.setBuilding(buildingRepository.findById(invitationRequest.getBuildingId())
               .orElseThrow(null));
       invitation.setUser(userRepository.getUserById(invitationRequest.getUserID()));
       invitation.setJointStatus(invitationRequest.getJointStatus());
       invitationRepository.save(invitation);


    }



}
