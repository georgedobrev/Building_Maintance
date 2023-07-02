import { Theme } from "@mui/material";

export const footerStyles = (theme: Theme) => {
  return {
    container: {
      position: "fixed",
      left: "50%",
      bottom: 0,
      transform: "translateX(-50%)",
      width: "100%",
      maxWidth: "600px",
      color: theme.palette.primary.main,
      textAlign: "center",
      padding: "10px 0",
      backdropFilter: "blur(10px)",
    },
  };
};
