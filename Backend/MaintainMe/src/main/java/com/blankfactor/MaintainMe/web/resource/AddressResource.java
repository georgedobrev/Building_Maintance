package com.blankfactor.MaintainMe.web.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
    public class AddressResource {
        private long id;
        private String country;
        private String region;
        private String city;
        private String district;
        private Integer postalCode;
        private String streetName;
        private Integer streetNumber;
}
