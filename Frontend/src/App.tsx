import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useSelector } from "react-redux";
import { ThemeProvider } from "@mui/material/styles";
import Login from "./pages/login/Login";
import Register from "./pages/register/registerUser/Register";
import Notifications from "./pages/notifications/Notifications";
import Home from "./pages/home/Home";
import Navbar from "../src/components/navbar/Navbar";
import createMyTheme from "./Theme";
import Announcements from "./pages/announcements/Announcements";
import { RootState } from "./store/store";
import Users from "./pages/users/Users";
import AddBuilding from "./pages/addBuilding/AddBuilding";
import Footer from "./components/Footer/Footer";
import { selectRole } from "./store/loggedUser/loggedUser";
import AdminRegister from "./pages/register/registerManager/AdminRegister";

let manager = false;
let currentUser = false;
const App = () => {
  const isDarkMode = useSelector((state: RootState) => state.theme.darkMode);
  const theme = createMyTheme(isDarkMode);
  const role = useSelector(selectRole);

  if (role === 1) {
    currentUser = true;
  }
  if (role === 2) {
    manager = true;
  }

  return (
    <ThemeProvider theme={theme}>
      <BrowserRouter>
        <Navbar manager={manager} currentUser={currentUser} />
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
              element={<Announcements currentUser={currentUser} />}
            />
          )}

          {manager && <Route path="/addbuilding" element={<AddBuilding />} />}
          {!currentUser && !manager && (
            <Route path="/register-admin" element={<AdminRegister />} />
          )}
          <Route path="/users" element={<Users />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </ThemeProvider>
  );
};

export default App;
