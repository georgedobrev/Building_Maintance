import { configureStore } from "@reduxjs/toolkit";
import themeReducer from "./DarkMode/themeSlice";
import notificationReducer from "./notification/notificationSlice";
import commentReducer from "./comment/commentSlice";
import userReducer from "./users/userSlice";
import paymentReducer from "./payment/paymentSlice";

export const store = configureStore({
  reducer: {
    theme: themeReducer,
    notification: notificationReducer,
    comment: commentReducer,
    user: userReducer,
    payment: paymentReducer
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
