import { createTheme } from "@mui/material/styles";
import { blue, grey, teal, cyan } from "@mui/material/colors";
const Theme = (isDarkMode: boolean) => {
  return createTheme({
    palette: {
      mode: isDarkMode ? "dark" : "light",
      primary: {
        main: isDarkMode ? teal[300] : cyan[800],
      },
      secondary: {
        main: isDarkMode ? cyan[300] : cyan[50],
      },
    },
  });
};
export default Theme;
