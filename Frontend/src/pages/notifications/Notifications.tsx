import { FC } from "react";
import { useSelector } from "react-redux";
import { CssBaseline, Container } from "@mui/material";
import NotificationCard from "../../components/UI/NotificationCard";
import { selectNotifications } from "../../store/notification/notificationSlice";

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
            key={notification.id}
            currentUser={currentUser}
            title={notification.title}
            description={notification.description}
          />
        ))}
      </Container>
    </>
  );
};
export default Notifications;
