import * as React from "react";
import { Tabs, Tab } from "@mui/material";
import { styled } from "@mui/system";
import { useDispatch } from "react-redux";

const StyledTabs = styled(Tabs)(({ theme }) => ({
  color: theme.palette.primary.main,
  borderBottom: `1px solid ${theme.palette.primary.main}`,
}));

const StyledTab = styled(Tab)(({ theme }) => ({
  color: theme.palette.primary.main,
  "&.Mui-selected": {
    color: theme.palette.primary.main,
  },
}));

export default function DisabledTabs() {
  const [value, setValue] = React.useState(0);
  const dispatch = useDispatch();

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {};

  return (
    <StyledTabs
      value={value}
      onChange={handleChange}
      aria-label="disabled tabs example"
    >
      <StyledTab label="Private" />
      <StyledTab label="Public" />
    </StyledTabs>
  );
}
