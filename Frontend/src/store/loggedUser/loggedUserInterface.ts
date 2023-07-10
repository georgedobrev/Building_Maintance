export interface CurrentUser {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
}

export interface LoggedUser {
  currentUser: CurrentUser | null;
  token: string | null;
  role: number | null;
}
