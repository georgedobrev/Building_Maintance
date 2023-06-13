import { configureStore } from "@reduxjs/toolkit";
import themeReducer from "./DarkMode/themeSlice";
import notificationReducer from "./notification/notificationSlice";

export const store = configureStore({
  reducer: {
    theme: themeReducer,
    notification: notificationReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
