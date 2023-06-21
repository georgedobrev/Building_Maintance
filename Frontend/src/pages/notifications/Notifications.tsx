import { FC } from "react";
import { useSelector } from "react-redux";
import { CssBaseline, Container } from "@mui/material";
import { selectNotifications } from "../../store/notification/notificationSlice";
import NotificationCard from "../../components/NotificationUI/NotificationCard";

interface NotificationProps {
  currentUser: boolean;
}

const Notifications: FC<NotificationProps> = ({ currentUser }) => {
  const notifications = useSelector(selectNotifications);
  return (
    <>
      <CssBaseline />
      <Container maxWidth="sm" sx={{}}>
        {notifications.map((notification) => (
          <NotificationCard
            id={notification.id}
            key={notification.id}
            currentUser={currentUser}
            title={notification.title}
            description={notification.description}
            date={notification.date}
          />
        ))}
      </Container>
    </>
  );
};
export default Notifications;
