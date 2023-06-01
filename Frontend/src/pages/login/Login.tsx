import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Paper from "@mui/material/Paper";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import { IconButton, useTheme } from "@mui/material";
import BuildingPic from "../../assets/backgroundBFM.jpg";
import InputAdornment from "@mui/material/InputAdornment";
import VisibilityOutlinedIcon from "@mui/icons-material/VisibilityOutlined";

const SignInSide = () => {
  const theme = useTheme();

  const handleSubmit = () => {
    console.log("Submitted");
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
          flexDirection: "row",
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
              width: "80%",
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
              sx={{ width: "60%", mx: "auto", mt: 3 }}
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
              sx={{ mt: 1, width: "100%", "& > *": { mb: 2 } }}
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
              <Box
                sx={{
                  display: "flex",
                  justifyContent: "space-between",
                  mt: 1,
                  "& > *": { mb: 2 },
                }}
              >
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Box>
            </Box>
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default SignInSide;
