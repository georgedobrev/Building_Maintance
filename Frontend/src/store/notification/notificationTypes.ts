export interface Notification {
  id: number;
  title: string;
  description: string;
  assignTo: string;
}

export interface NotificationState {
  notifications: Notification[];
}
