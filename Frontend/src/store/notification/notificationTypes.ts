export interface Notification {
  date: string;
  id: number;
  title: string;
  description: string;
  assignTo: string;
}

export interface NotificationState {
  notifications: Notification[];
}
