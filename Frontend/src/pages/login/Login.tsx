import { ChangeEvent } from "react";
import {
  IconButton,
  useTheme,
  Box,
  Button,
  Checkbox,
  CssBaseline,
  FormControlLabel,
  InputAdornment,
  Link,
  Paper,
  TextField,
  Typography,
} from "@mui/material";
import VisibilityOutlinedIcon from "@mui/icons-material/VisibilityOutlined";
import BuildingPic from "../../assets/backgroundBFM.jpg";
import { FormValues } from "./LoginInterfaces";
import useAuthValidations from "../../common/utils";
import GoogleButton from "./GoogleButton";

const SignInSide = () => {
  const theme = useTheme();

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

  const handleClickShowPassword = () => {};

  return (
    <>
      <CssBaseline />
      <Box
        sx={{
          display: "flex",
          width: "100vw",
          height: "100vh",
          flexDirection: { xs: "column", sm: "row" },
          alignItems: "stretch",
        }}
      >
        <Box
          sx={{
            flex: "1 1 50%",
            backgroundImage: `url(${BuildingPic})`,
            backgroundRepeat: "no-repeat",
            backgroundSize: "cover",
            backgroundPosition: "center",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            color: "whitesmoke",
            position: "relative",
            "::after": {
              content: '""',
              display: "block",
              position: "absolute",
              top: 0,
              bottom: 0,
              left: 0,
              right: 0,
              backgroundColor: "rgba(0, 0, 0, 0.4)",
            },
            zIndex: 1,
            p: theme.spacing(3),
            "& > *": { mb: 2 },
          }}
        >
          <Box
            sx={{
              position: "relative",
              zIndex: 2,
              textAlign: "center",
              width: { xs: "100%", sm: "80%" },
              "& > *": { mb: 2 },
            }}
          >
            <Typography
              component="h1"
              variant="h4"
              sx={{ mb: 3, fontSize: "2.5rem" }}
            >
              Welcome to Blankfactor&apos;s building maintainance app.
            </Typography>
            <Typography
              component="p"
              variant="body1"
              sx={{ width: { xs: "80%", sm: "60%" }, mx: "auto", mt: 3 }}
            >
              This is the place to connect to your neighbours, track and manage
              payments, promote ideas and address issues you come accross.
            </Typography>
          </Box>
        </Box>
        <Box
          component={Paper}
          elevation={6}
          square
          sx={{
            flex: "1 1 50%",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            p: theme.spacing(3),
            "& > *": { mb: 2 },
          }}
        >
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              "& > *": { mb: 2 },
            }}
          >
            <Typography
              component="h1"
              variant="h4"
              sx={{ color: theme.palette.primary.main }}
            >
              Login
            </Typography>
            <Box
              component="form"
              noValidate
              onSubmit={handleSubmit}
              sx={{
                mt: 1,
                width: { xs: "80%", sm: "50%" },
                "& > *": { mb: 2 },
              }}
            >
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                autoFocus
                value={formValues.email}
                onChange={(e: ChangeEvent<HTMLInputElement>) =>
                  handleChange("email", e)
                }
                onBlur={() => validateField("email")}
                error={!!formErrors.email}
                helperText={formErrors.email}
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                value={formValues.password}
                onChange={(e: ChangeEvent<HTMLInputElement>) =>
                  handleChange("password", e)
                }
                onBlur={() => validateField("password")}
                error={!!formErrors.password}
                helperText={formErrors.password}
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton onClick={handleClickShowPassword} edge="end">
                        <VisibilityOutlinedIcon />
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
              />
              <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Remember me"
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Log In
              </Button>
              <GoogleButton />
            </Box>
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default SignInSide;
