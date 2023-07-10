import { useTheme, Box, Button } from "@mui/material";
import { useState } from "react";
import Notifications from "../notifications/Notifications";
import CampaignOutlinedIcon from "@mui/icons-material/CampaignOutlined";
import CreateAnnouncement from "./CreateAnnouncement";

interface AnnouncementsProps {
  currentUser: boolean;
}

const Announcements = ({ currentUser }: AnnouncementsProps) => {
  const theme = useTheme();
  const [open, setOpen] = useState(false);

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",

        marginTop: "100px",
        width: "100%",
      }}
    >
      <Button
        sx={{
          color:
            theme.palette.mode === "dark"
              ? theme.palette.primary.main
              : theme.palette.primary.main,
        }}
        size="large"
        startIcon={<CampaignOutlinedIcon />}
        onClick={() => setOpen(true)}
      >
        Create Announcement
      </Button>
      <Box sx={{ mt: -10, width: "100%" }}>
        <CreateAnnouncement open={open} setOpen={setOpen} />
        <Notifications currentUser={currentUser} />
      </Box>
    </Box>
  );
};

export default Announcements;
