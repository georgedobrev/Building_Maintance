import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store";

export interface Payment {
  id: number;
  date: string;
  invoiceNumber: string;
  paymentType: string;
  paymentMethod: string;
  totalPrice: number;
}

export type PaymentState = Payment[];

const initialState: PaymentState = [];

export const paymentSlice = createSlice({
  name: "payment",
  initialState,
  reducers: {
    addPayment: (state, action: PayloadAction<Payment>) => {
      state.push(action.payload);
    },
    deletePayment: (state, action: PayloadAction<number>) => {
      return state.filter((payment) => payment.id !== action.payload);
    },
    editPayment: (
      state,
      action: PayloadAction<{ id: number; payment: Partial<Payment> }>
    ) => {
      const paymentToEdit = state.find(
        (payment) => payment.id === action.payload.id
      );
      if (paymentToEdit) {
        Object.assign(paymentToEdit, action.payload.payment);
      }
    },
  },
});

export const { addPayment, deletePayment, editPayment } = paymentSlice.actions;

export const selectPayments = (state: RootState) => state.payment;

export default paymentSlice.reducer;
