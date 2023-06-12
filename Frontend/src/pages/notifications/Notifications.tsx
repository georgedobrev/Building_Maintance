import * as React from "react";
import { useSelector } from "react-redux";
import { CssBaseline, Container } from "@mui/material";
import NotificationCard from "../../components/UI/NotificationCard";
import { selectNotifications } from "../../store/notification/notificationSlice";

interface NotificationProps {
  currentUser: boolean;
}

function Notifications({ currentUser }: NotificationProps) {
  const notifications = useSelector(selectNotifications);
  return (
    <React.Fragment>
      <CssBaseline />
      <Container maxWidth="sm" sx={{}}>
        {notifications.map((notification, index) => (
          <NotificationCard
            key={index}
            currentUser={currentUser}
            title={notification.title}
            description={notification.description}
          />
        ))}
      </Container>
    </React.Fragment>
  );
}
export default Notifications;
