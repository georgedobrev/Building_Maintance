import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { CommentState, Comment } from "../comment/commentTypes";

const initialState: CommentState = [];

export const commentSlice = createSlice({
  name: "comment",
  initialState,
  reducers: {
    addComment: (state, action: PayloadAction<Comment>) => {
      state.push(action.payload);
    },
    deleteComment: (state, action: PayloadAction<number>) => {
      return state.filter((comment) => comment.id !== action.payload);
    },
    editComment: (
      state,
      action: PayloadAction<{ id: number; text: string }>
    ) => {
      const commentToEdit = state.find(
        (comment) => comment.id === action.payload.id
      );
      if (commentToEdit) {
        commentToEdit.text = action.payload.text;
      }
    },
  },
});

export const { addComment, deleteComment, editComment } = commentSlice.actions;

export const selectComments = (state: RootState) => state.comment;

export default commentSlice.reducer;
