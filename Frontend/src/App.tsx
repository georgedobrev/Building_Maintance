import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import { useSelector } from "react-redux";
import { ThemeProvider } from "@mui/material/styles";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Notifications from "./pages/notifications/Notifications";
import Home from "./pages/home/Home";
import Navbar from "../src/components/navbar/Navbar";
import createMyTheme from "./Theme";
import Announcements from "./pages/announcements/Announcements";
import { RootState } from "./store/store";
import Users from "./pages/users/Users";
import AddBuilding from "./pages/addBuilding/AddBuilding";
import AdminRegister from "./pages/register/AdminRegister";
import Footer from "./components/Footer/Footer";
const currentUser = false;
const manager = false;

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
          <Route
            path="/register"
            element={<Register currentUser={currentUser} manager={manager} />}
          />
          {currentUser && (
            <>
              <Route
                path="/notifications"
                element={<Notifications currentUser={currentUser} />}
              />
              <Route path="/payments" element={<Payments />} />
            </>
          )}
          {manager && (
            <Route
              path="/announcements"
              element={<Announcements currentUser={currentUser} />}
            />
          )}
          {manager && <Route path="/add-unit" element={<AddUnit />} />}
          {manager && <Route path="/addbuilding" element={<AddBuilding />} />}
          {!currentUser && !manager && (
            <Route
              path="/register-admin"
              element={
                <AdminRegister currentUser={currentUser} manager={manager} />
              }
            />
          )}
          <Route path="/users" element={<Users />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </ThemeProvider>
  );
};

export default App;
