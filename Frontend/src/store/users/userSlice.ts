import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store";

interface User {
  firstName: string;
  lastName: string;
  email: string;
  building: number | string;
}

export interface UsersState {
  users: User[];
}

const initialState: UsersState = {
  users: [],
};

export const selectUsers = (state: RootState) => state.user.users;

export const usersSlice = createSlice({
  name: "users",
  initialState,
  reducers: {
    addUser: (state, action: PayloadAction<User>) => {
      state.users.push(action.payload);
    },
  },
});

export const { addUser } = usersSlice.actions;

export default usersSlice.reducer;