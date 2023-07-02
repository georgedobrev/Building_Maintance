import { CSSProperties } from "react";
import { Container, Typography, useTheme } from "@mui/material";
import { footerStyles } from "./styles";

const Footer = () => {
  const theme = useTheme();
  const styles = footerStyles(theme);

  return (
    <Container maxWidth="sm" style={styles.container as CSSProperties}>
      <Typography variant="body2" color="inherit" align="center">
        {"Copyright © "}
        BlankFactor {new Date().getFullYear()}
        {"."}
      </Typography>
    </Container>
  );
};

export default Footer;
