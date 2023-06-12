import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import { useSelector } from "react-redux";
import { ThemeProvider } from "@mui/material/styles";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Navbar from "./components/navbar/Navbar";
import createMyTheme from "./Theme";
import Home from "./pages/home/Home";
import { RootState } from "../src/store/DarkMode/store";

const currentUser = true;

const NavbarWrapper = () => {
  const location = useLocation();
  const isLoginPage = location.pathname === "/login";
  return isLoginPage ? null : <Navbar currentUser={currentUser} />;
};

const App = () => {
  const isDarkMode = useSelector((state: RootState) => state.theme.darkMode);
  const theme = createMyTheme(isDarkMode);

  return (
    <ThemeProvider theme={theme}>
      <BrowserRouter>
        <NavbarWrapper />
        <Routes>
          <Route path="/" element={<Home currentUser={currentUser} />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
};

export default App;
