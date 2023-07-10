import React from "react";
import homePagePic from "../../assets/homePageView.jpg";
import logo from "../../assets/blankfactor-logo.svg";
import { useDispatch } from "react-redux";
import { setRole } from "../../store/loggedUser/loggedUser";
import { authService } from "../../services/authService";

interface HomeProps {
  manager: boolean;
  currentUser: boolean;
}

const Home: React.FC<HomeProps> = () => {
  const dispatch = useDispatch();
  const token = localStorage.getItem("token");
  if (token) {
    const getRole = async () => {
      let roleResponse;
      try {
        roleResponse = await authService.getUserRole();
        dispatch(setRole(roleResponse.roleId));
      } catch (roleError) {}
      return roleResponse ? roleResponse.roleId : null;
    };

    getRole();
  }

  return (
    <div
      style={{
        position: "relative",
        height: "100vh",
        width: "100vw",
        overflow: "hidden",
      }}
    >
      <div
        style={{
          position: "absolute",
          top: 0,
          left: 0,
          height: "100%",
          width: "100%",
          backgroundColor: "rgba(0, 0, 0, 0.7)",
          zIndex: 1,
        }}
      />
      <img
        src={homePagePic}
        alt="Background"
        style={{
          position: "absolute",
          top: "50%",
          left: "50%",
          height: "100%",
          width: "100%",
          transform: "translate(-50%, -50%)",
          objectFit: "cover",
          zIndex: 0,
        }}
      />
      <img
        src={logo}
        alt="Company Logo"
        style={{
          position: "absolute",
          top: "40%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          zIndex: 2,
        }}
      />
      <h2
        style={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          color: "#ffffff",
          textAlign: "center",
          zIndex: 2,
        }}
      >
        The original homepage view for BlankFactor's Maintenance app is coming
        soon...
      </h2>
    </div>
  );
};

export default Home;
