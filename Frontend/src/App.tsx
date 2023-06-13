import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import { useSelector } from "react-redux";
import { ThemeProvider } from "@mui/material/styles";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Notifications from "./pages/notifications/Notifications";
import Home from "./pages/home/Home";
import Navbar from "./components/Navbar";
import createMyTheme from "./Theme";
import Announcements from "./pages/announcements/Announcements";
import CreateAnnouncement from "./pages/announcements/CreateAnnouncement";
import { RootState } from "./store/store";
import Users from "./pages/users/Users";

const currentUser = false;
const manager = true;

const NavbarWrapper = () => {
  const location = useLocation();

  const isLoginPage = location.pathname === "/login";

  return isLoginPage ? null : (
    <Navbar manager={manager} currentUser={currentUser} />
  );
};

const App = () => {
  const isDarkMode = useSelector((state: RootState) => state.theme.darkMode);
  const theme = createMyTheme(isDarkMode);

  return (
    <ThemeProvider theme={theme}>
      <BrowserRouter>
        <NavbarWrapper />
        <Routes>
          <Route
            path="/"
            element={<Home manager={manager} currentUser={currentUser} />}
          />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          {currentUser && (
            <Route
              path="/notifications"
              element={<Notifications currentUser={currentUser} />}
            />
          )}
          {manager && (
            <Route
              path="/announcements"
              element={<Announcements manager={manager} />}
            />
          )}
          <Route
            path="/create/announcements"
            element={<CreateAnnouncement />}
          />
          <Route path="/users" element={<Users />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
};

export default App;
