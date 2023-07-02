export interface Address {
  country: string;
  region: string;
  city: string;
  district: string;
  postalCode: number | null;
  streetName: string;
  streetNumber: number | null;
}

export interface BuildingResource {
  name: string;
  address: Address;
  floors: number | null;
  entrances: number | null;
}

export interface RegistrationRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

export interface RegisterAdminData {
  buildingResource: BuildingResource;
  registrationRequest: RegistrationRequest;
}
