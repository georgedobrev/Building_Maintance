export interface Notification {
  title: string;
  description: string;
  assignTo: string;
}

export interface NotificationState {
  notifications: Notification[];
}
