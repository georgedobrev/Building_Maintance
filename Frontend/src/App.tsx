import { useState } from "react";
import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import Profile from "./pages/profile/Profile";
import Account from "./pages/account/Account";
import Pricing from "./pages/pricing/Pricing";
import Products from "./pages/products/Products";
import { ThemeProvider } from "@mui/material/styles";
import Payments from "./pages/payments/Payments";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Notifications from "./pages/announcements/Notifications";
import About from "./pages/about/About";
import Home from "./pages/home/Home";
import Navbar from "./components/Navbar";
import createMyTheme from "./Theme";

const currentUser = true;

const NavbarWrapper = () => {
  const location = useLocation();
  const isLoginPage = location.pathname === "/login";
  return isLoginPage ? null : <Navbar currentUser={currentUser} />;
};

const App = () => {
  const [isDarkMode, setIsDarkMode] = useState(false);
  const theme = createMyTheme(isDarkMode);

  return (
    <ThemeProvider theme={theme}>
      <BrowserRouter>
        <NavbarWrapper />
        <Routes>
          <Route path="/" element={<Home currentUser={currentUser} />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/notifications" element={<Notifications />} />
          <Route path="/payments" element={<Payments />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/account" element={<Account />} />
          <Route path="/aboutus" element={<About />} />
          <Route path="/pricing" element={<Pricing />} />
          <Route path="/products" element={<Products />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
};

export default App;
