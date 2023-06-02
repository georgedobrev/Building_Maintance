export interface FormValues {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}

export interface FormErrors {
  firstName?: string;
  lastName?: string;
  email?: string;
  password?: string;
}
