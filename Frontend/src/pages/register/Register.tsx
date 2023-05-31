import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import { useTheme } from "@mui/material/styles";
import Typography from "@mui/material/Typography";
import SelectField from "./SelectField";

const Register = () => {
  const theme = useTheme();

  return (
    <Box
      display="flex"
      flexDirection="column"
      justifyContent="center"
      alignItems="center"
      minHeight={`calc(100vh - 64px)`}
      bgcolor={theme.palette.background.default}
    >
      <Typography
        variant="h4"
        component="h1"
        gutterBottom
        sx={{
          mt: "8%",
          fontFamily: theme.typography.fontFamily,
          fontWeight: 700,
          color: theme.palette.primary.main,
          textDecoration: "none",
        }}
      >
        Register User
      </Typography>
      <Box
        component="form"
        sx={{
          mt: "1%",
          height: "100vh",
          width: "40vw",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: 6,
        }}
      >
        <TextField
          placeholder="First Name"
          variant="outlined"
          sx={{ width: "100%" }}
        />
        <TextField
          placeholder="Last Name"
          variant="outlined"
          sx={{ width: "100%" }}
        />
        <TextField
          placeholder="Email"
          variant="outlined"
          sx={{ width: "100%" }}
        />
        <TextField
          placeholder="Password"
          type="password"
          variant="outlined"
          sx={{ width: "100%" }}
        />
        <SelectField />
        <Button
          type="submit"
          variant="contained"
          color="primary"
          sx={{ width: "100%" }}
        >
          Register
        </Button>
      </Box>
    </Box>
  );
};
export default Register;
