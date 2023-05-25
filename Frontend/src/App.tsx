import React from "react";
import { createBrowserRouter, Outlet, RouterProvider } from "react-router-dom";
import Login from "./pages/login/Login";
import Home from "./pages/home/Home";
import Register from "./pages/register/Register";
import Navbar from "./components/UI/Navbar";
import Profile from "./pages/profile/Profile";
import Products from "./pages/products/Products";
import Account from "./pages/account/Account";
import Announcements from "./pages/announcements/Announcements";
import Payments from "./pages/payments/Payments";
import CreatePost from "./pages/create-post/CreatePost";
import Pricing from "./pages/pricing/Pricing";
import About from "./pages/about/About";

const App: React.FC = () => {
  const Layout: React.FC = () => {
    return (
      <div>
        <Navbar />
        <div style={{ display: "flex" }}>
          <Outlet />
        </div>
      </div>
    );
  };

  const routes = [
    {
      path: "/",
      element: <Layout />,
      children: [
        { path: "/", element: <Home /> },
        { path: "/profile/:id", element: <Profile /> },
        { path: "/account/:id", element: <Account /> },
      ],
    },
    { path: "/login", element: <Login /> },
    { path: "/register", element: <Register /> },
    { path: "/announcements", element: <Announcements /> },
    { path: "/payments", element: <Payments /> },
    { path: "/createapost", element: <CreatePost /> },
    { path: "/pricing", element: <Pricing /> },
    { path: "/aboutus", element: <About /> },
    { path: "/products", element: <Products /> },
  ];

  const router = createBrowserRouter(routes);

  return (
    <div>
      <RouterProvider router={router} />
    </div>
  );
};

export default App;
