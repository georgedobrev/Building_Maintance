import { useState } from "react";
import {
  Theme,
  useTheme,
  OutlinedInput,
  InputLabel,
  MenuItem,
  FormControl,
  Select,
} from "@mui/material";
import Users from "../users/Users.json";

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

const getStyles = (name: string, personName: string[], theme: Theme) => {
  return {
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
};

interface SelectFieldProps {
  label: string;
  items: string[];
  value: number | string;
  onChange: (value: number | string) => void;
}

const SelectField: React.FC<SelectFieldProps> = ({
  label,
  items,
  value,
  onChange,
}) => {
  const theme = useTheme();

  const handleChange = ({
    target: { value },
  }: {
    target: { value: unknown };
  }) => {
    onChange(value as number | string);
  };

  return (
    <FormControl sx={{ m: 1, width: "100%" }}>
      <InputLabel id="demo-multiple-name-label">{label}</InputLabel>
      <Select
        labelId="demo-multiple-name-label"
        id="demo-multiple-name"
        value={value}
        onChange={handleChange}
        input={<OutlinedInput label="Name" />}
        MenuProps={MenuProps}
      >
        {items.map((name) => (
          <MenuItem
            key={name}
            value={name}
            style={getStyles(name, items, theme)}
          >
            {name}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default SelectField;
