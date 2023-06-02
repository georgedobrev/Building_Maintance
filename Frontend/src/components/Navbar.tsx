import { useState } from "react";
import { Link } from "react-router-dom";
import MenuIcon from "@mui/icons-material/Menu";
import { styled, useTheme } from "@mui/system";
import {
  AppBar,
  Box,
  Toolbar,
  IconButton,
  Typography,
  Menu,
  Container,
  Avatar,
  Button,
  Tooltip,
  MenuItem,
} from "@mui/material";

const StyledLink = styled(Link)(({ theme }) => ({
  textDecoration: "none",
  color:
    theme.palette.mode === "dark"
      ? theme.palette.primary.main
      : theme.palette.secondary.main,
}));

const StyledDropdownLink = styled(Link)(({ theme }) => ({
  textDecoration: "none",
  color:
    theme.palette.mode === "dark"
      ? theme.palette.primary.main
      : theme.palette.primary.main,
}));

const Navbar = ({
  currentUser,
  manager,
  serviceCompanyAdmin,
  serviceCompany,
}: NavbarProps) => {
  const [anchorElNav, setAnchorElNav] = useState<null | HTMLButtonElement>(
    null
  );
  const [anchorElUser, setAnchorElUser] = useState<null | HTMLButtonElement>(
    null
  );

  const theme = useTheme();

  const handleOpenNavMenu = (
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleOpenUserMenu = (
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  let pages = ["Notifications", "Payments"];
  let settings = ["Profile", "Account", "Logout"];

  if (!currentUser) {
    pages = ["About us", "Pricing", "Login"];
    settings = ["Login", "Products"];
  }

  if (manager) {
    pages = ["Announcements", "Users", "Add User", "Payments", "Auctions"];
    settings = ["Profile", "Account", "Logout"];
  }

  if (serviceCompanyAdmin) {
    pages = ["Companies", "Add Company"];
    settings = ["Profile", "Account", "Logout"];
  }

  if (serviceCompany) {
    pages = ["Auctions", "Requests"];
    settings = ["Profile", "Account", "Logout"];
  }

  return (
    <AppBar position="fixed">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Typography
            variant="h6"
            noWrap
            component={StyledLink}
            to="/"
            sx={{
              mr: 2,
              display: { xs: "none", md: "flex" },
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
            }}
          >
            BLANKFACTOR
          </Typography>
          <Box
            sx={{
              flexGrow: 1,
              display: {
                xs: "block",
                md: "none",
              },
            }}
          >
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              sx={{
                color:
                  theme.palette.mode === "dark"
                    ? theme.palette.primary.main
                    : theme.palette.grey[50],
              }}
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "bottom",
                horizontal: "left",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "left",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              PaperProps={{
                style: {
                  zIndex: 1300,
                },
              }}
              sx={{
                color:
                  theme.palette.mode === "dark"
                    ? theme.palette.grey[500]
                    : theme.palette.grey[50],
              }}
            >
              {pages.map((page) => (
                <MenuItem key={page} onClick={handleCloseNavMenu}>
                  <StyledDropdownLink
                    to={`/${page.replace(/\s/g, "").toLowerCase()}`}
                  >
                    {page}
                  </StyledDropdownLink>
                </MenuItem>
              ))}
            </Menu>
          </Box>
          <Typography
            variant="h5"
            noWrap
            component={StyledLink}
            to=""
            sx={{
              mr: 2,
              display: { xs: "flex", md: "none" },
              flexGrow: 1,
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
            }}
          >
            BLANKFACTOR
          </Typography>
          <Box
            sx={{
              flexGrow: 1,
              display: { xs: "none", md: "flex" },
              justifyContent: "flex-end",
            }}
          >
            {pages.map((page) => (
              <Button
                key={page}
                onClick={handleCloseNavMenu}
                sx={{
                  my: 2,
                  mx: 2,
                  display: "block",
                  transition: "transform 0.3s",
                  "&:hover": {
                    transform: "translateY(-3px)",
                  },
                }}
              >
                <StyledLink to={`/${page.replace(/\s/g, "").toLowerCase()}`}>
                  {page}
                </StyledLink>
              </Button>
            ))}
          </Box>
          <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                <Avatar
                  alt="Remy Sharp"
                  src="https://images.pexels.com/photos/1851164/pexels-photo-1851164.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" //just for testing
                />
              </IconButton>
            </Tooltip>
            <Menu
              sx={{ mt: "44px" }}
              id="menu-appbar"
              anchorEl={anchorElUser}
              anchorOrigin={{
                vertical: "top",
                horizontal: "right",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "right",
              }}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
            >
              {settings.map((setting) => (
                <MenuItem key={setting} onClick={handleCloseUserMenu}>
                  <Typography textAlign="center">
                    <StyledDropdownLink to={`/${setting.toLowerCase()}`}>
                      {setting}
                    </StyledDropdownLink>
                  </Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
};
export default Navbar;
