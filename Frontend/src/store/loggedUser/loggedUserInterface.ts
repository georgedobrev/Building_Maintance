export interface LoggedUser {
  currentUser: {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
  } | null;
  token: string | null;
  role: number | null;
}
