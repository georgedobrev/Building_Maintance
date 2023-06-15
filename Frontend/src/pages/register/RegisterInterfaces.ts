export interface FormValues {
  firstName: string;
  lastName: string;
  email: string;
  building: string | number;
}

export interface FormErrors {
  firstName?: string;
  lastName?: string;
  email?: string;
  building?: string;
}
