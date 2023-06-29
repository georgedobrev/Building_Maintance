import { FC } from "react";
import { useSelector } from "react-redux";
import {
  CssBaseline,
  Container,
  Typography,
  useTheme,
  Box,
} from "@mui/material";
import NotificationsOffIcon from "@mui/icons-material/NotificationsOff";
import { selectNotifications } from "../../store/notification/notificationSlice";
import NotificationCard from "../../components/NotificationUI/NotificationCard";

interface NotificationProps {
  currentUser: boolean;
}

const Notifications: FC<NotificationProps> = ({ currentUser }) => {
  const notifications = useSelector(selectNotifications);
  const theme = useTheme();

  if (notifications.length === 0) {
    return (
      <Box
        sx={{
          width: "100%",
          height: "100vh",
          bgcolor: theme.palette.background.default,
          color: theme.palette.text.primary,
        }}
      >
        <Container
          maxWidth="sm"
          style={{
            textAlign: "center",
          }}
        >
          <NotificationsOffIcon
            style={{
              marginTop: "200px",
              fontSize: 60,
              color: theme.palette.primary.main,
            }}
          />
          <Typography
            variant="h5"
            style={{ color: theme.palette.primary.main }}
          >
            {!currentUser
              ? " There are no announcements to display"
              : " There are no notifications to display"}
          </Typography>
        </Container>
      </Box>
    );
  }

  return (
    <>
      <CssBaseline />
      <Container maxWidth="sm">
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
