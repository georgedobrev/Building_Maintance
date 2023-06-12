import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store";
import {
  NotificationState,
  Notification,
} from "../notification/notificationTypes";

const initialState: NotificationState = {
  notifications: [],
};

export const notificationSlice = createSlice({
  name: "notification",
  initialState,
  reducers: {
    addNotification: (state, action: PayloadAction<Notification>) => {
      state.notifications.push(action.payload);
    },
  },
});

export const { addNotification } = notificationSlice.actions;

export const selectNotifications = (state: RootState) =>
  state.notification.notifications;

export default notificationSlice.reducer;
