export interface Building {
  building: string;
  buildingName: string;
  country: string;
  region: string;
  city: string;
  district: string;
  postalCode: number | null;
  streetName: string;
  streetNumber: number | null;
  floors: number | null;
  entrances: number | null;
  [key: string]: string | number | null;
}
