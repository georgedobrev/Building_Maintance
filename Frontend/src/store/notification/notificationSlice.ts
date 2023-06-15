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
    deleteNotification: (state, action: PayloadAction<number>) => {
      state.notifications = state.notifications.filter(
        (notification) => notification.id !== action.payload
      );
    },
    editNotification: (
      state,
      action: PayloadAction<{ id: number; title: string; description: string }>
    ) => {
      const notification = state.notifications.find(
        (notification) => notification.id === action.payload.id
      );
      if (notification) {
        notification.title = action.payload.title;
        notification.description = action.payload.description;
      }
    },
  },
});

export const { addNotification, deleteNotification, editNotification } =
  notificationSlice.actions;

export const selectNotifications = (state: RootState) =>
  state.notification.notifications;

export default notificationSlice.reducer;
