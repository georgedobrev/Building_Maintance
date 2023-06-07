import { ChangeEvent } from "react";
import {
  Box,
  Button,
  TextField,
  Typography,
  useTheme,
  useMediaQuery,
} from "@mui/material";
import SelectField from "./SelectField";
import useAuthValidations from "../../common/utils";
import { FormValues } from "./RegisterInterfaces";

const Register = () => {
  const theme = useTheme();
  const matches = useMediaQuery(theme.breakpoints.down("sm"));

  const { formValues, setFormValues, formErrors, validateField } =
    useAuthValidations();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    Object.keys(formValues).forEach((field) =>
      validateField(field as keyof FormValues)
    );
    console.log(formValues);
  };

  const handleChange = (
    fieldName: keyof FormValues,
    event: ChangeEvent<HTMLInputElement>
  ) => {
    setFormValues({
      ...formValues,
      [fieldName]: event.target.value,
    });
  };

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
          mt: matches ? "16%" : "8%",
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
        onSubmit={handleSubmit}
        sx={{
          mt: "1%",
          height: "100vh",
          width: matches ? "100vw" : "40vw",
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
          value={formValues.firstName}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            handleChange("firstName", e)
          }
          onBlur={() => validateField("firstName")}
          error={!!formErrors.firstName}
          helperText={formErrors.firstName}
        />
        <TextField
          placeholder="Last Name"
          variant="outlined"
          sx={{ width: "100%" }}
          value={formValues.lastName}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            handleChange("lastName", e)
          }
          onBlur={() => validateField("lastName")}
          error={!!formErrors.lastName}
          helperText={formErrors.lastName}
        />
        <TextField
          placeholder="Email"
          variant="outlined"
          sx={{ width: "100%" }}
          value={formValues.email}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            handleChange("email", e)
          }
          onBlur={() => validateField("email")}
          error={!!formErrors.email}
          helperText={formErrors.email}
        />
        <TextField
          placeholder="Password"
          type="password"
          variant="outlined"
          sx={{ width: "100%" }}
          value={formValues.password}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            handleChange("password", e)
          }
          onBlur={() => validateField("password")}
          error={!!formErrors.password}
          helperText={formErrors.password}
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
