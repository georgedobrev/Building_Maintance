export interface Building {
  name: string;
  floors: number | null;
  entrances: number | null;
  address: Address;
}

export interface Address {
  country: string;
  region: string;
  city: string;
  district: string;
  postalCode: number | null;
  streetName: string;
  streetNumber: number | null;
}
