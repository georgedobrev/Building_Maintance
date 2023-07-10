import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { LoggedUser } from "./loggedUserInterface";

const persistedToken = localStorage.getItem("jwt-token");

const initialState: LoggedUser = {
  currentUser: null,
  token: persistedToken,
  role: null,
};

export const userState = createSlice({
  name: "loggedUser",
  initialState,
  reducers: {
    setUser: (state, action: PayloadAction<LoggedUser["currentUser"]>) => {
      state.currentUser = action.payload;
    },

    setRole: (state, action: PayloadAction<LoggedUser["role"]>) => {
      state.role = action.payload;
      localStorage.setItem("role", JSON.stringify(action.payload));
    },
    logout: (state) => {
      state.currentUser = null;
      state.token = null;
      localStorage.removeItem("token");
      localStorage.removeItem("role");
    },
  },
});

export const { setUser, setRole, logout } = userState.actions;

export const selectUser = (state: RootState) => state.loggedUser.currentUser;
export const selectRole = (state: RootState) => state.loggedUser.role;

export default userState.reducer;
