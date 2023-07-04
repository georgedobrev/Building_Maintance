export interface RegisterUser {
  unitID: number;
  firstName: string;
  lastName: string;
  email: string;
  buildingID: number;
}

export interface UsersState {
  users: RegisterUser[];
}
