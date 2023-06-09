import { useSelector, useDispatch } from "react-redux";
import { IconButton, useTheme } from "@mui/material";
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import Brightness2OutlinedIcon from "@mui/icons-material/Brightness2Outlined";
import { RootState } from "../../store/DarkMode/store";
import { toggleTheme } from "../../store/DarkMode/themeSlice";

const DarkModeSwitch = () => {
  const isDarkMode = useSelector((state: RootState) => state.theme.darkMode);
  const dispatch = useDispatch();
  const theme = useTheme();

  return (
    <IconButton
      sx={{
        color:
          theme.palette.mode === "dark"
            ? theme.palette.primary.main
            : theme.palette.grey[50],
      }}
      onClick={() => dispatch(toggleTheme())}
    >
      {isDarkMode ? <LightModeOutlinedIcon /> : <Brightness2OutlinedIcon />}
    </IconButton>
  );
};

export default DarkModeSwitch;
