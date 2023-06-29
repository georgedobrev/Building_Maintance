import { Container, Typography, useTheme } from "@mui/material";

const Footer = () => {
  const theme = useTheme();
  return (
    <Container
      maxWidth="sm"
      style={{
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
      }}
    >
      <Typography variant="body2" color="inherit" align="center">
        {"Copyright © "}
        BlankFactor {new Date().getFullYear()}
        {"."}
      </Typography>
    </Container>
  );
};

export default Footer;
