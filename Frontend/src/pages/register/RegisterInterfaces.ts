export interface FormValues {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  building: string | number;
  buildingName: string;
  unit: number | string;
  country: string;
  region: string;
  city: string;
  district: string;
  postalCode: number | null;
  streetName: string;
  streetNumber: number | null;
  floors: number | null;
  entrances: number | null;
}

export interface FormErrors {
  firstName?: string;
  lastName?: string;
  email?: string;
  password?: string;
  building?: string | number;
  buildingName?: string;
  unit?: string | number;
  country?: string;
  region?: string;
  city?: string;
  district?: string;
  postalCode?: string;
  streetName?: string;
  streetNumber?: string;
  floors?: string;
  entrances?: string;
}