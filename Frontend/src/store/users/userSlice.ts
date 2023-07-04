import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store";
// import { CreateUser } from "./interfaces";
import { RegisterUser } from "../../pages/register/interface";

export interface UsersState {
  users: RegisterUser[];
}

const initialState: UsersState = {
  users: [],
};

export const selectUsers = (state: RootState) => state.user.users;

export const usersSlice = createSlice({
  name: "users",
  initialState,
  reducers: {
    addUser: (state, action: PayloadAction<RegisterUser>) => {
      state.users.push(action.payload);
    },
  },
});

export const { addUser } = usersSlice.actions;

export default usersSlice.reducer;
