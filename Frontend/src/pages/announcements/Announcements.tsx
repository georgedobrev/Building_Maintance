import { Link } from "react-router-dom";
import { useTheme, Box, Button } from "@mui/material";
import Notifications from "../notifications/Notifications";
import CampaignOutlinedIcon from "@mui/icons-material/CampaignOutlined";

interface AnnouncementsProps {
  manager: boolean;
}

const Announcements = ({ manager }: AnnouncementsProps) => {
  const theme = useTheme();

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        marginTop: "6%",
        width: "100%",
      }}
    >
      {manager && (
        <Link to="/create/announcements">
          <Button
            sx={{
              color:
                theme.palette.mode === "dark"
                  ? theme.palette.primary.main
                  : theme.palette.primary.main,
            }}
            size="large"
            startIcon={<CampaignOutlinedIcon />}
          >
            Create Announcement
          </Button>
        </Link>
      )}
      <Box sx={{ mt: -10, width: "100%" }}>
        <Notifications />
      </Box>
    </Box>
  );
};

export default Announcements;
