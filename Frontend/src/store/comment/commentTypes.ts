export interface Comment {
  id: number;
  notificationId: number;
  text: string;
  commenter: string;
}

export type CommentState = Comment[];
