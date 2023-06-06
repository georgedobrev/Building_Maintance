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

const buildings = [
  "Oliver Hansen",
  "Van Henry",
  "April Tucker",
  "Ralph Hubbard",
  "Omar Alexander",
  "Carlos Abbott",
  "Miriam Wagner",
  "Bradley Wilkerson",
  "Virginia Andrews",
  "Kelly Snyder",
];

const getStyles = (name: string, personName: string[], theme: Theme) => {
  return {
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
};

const SelectField = () => {
  const theme = useTheme();
  const [names, setNames] = useState<string[]>([]);

  const handleChange = ({
    target: { value },
  }: {
    target: { value: string[] };
  }) => {
    setNames(value);
  };

  return (
    <FormControl sx={{ m: 1, width: "40vw" }}>
      <InputLabel id="demo-multiple-name-label">Building</InputLabel>
      <Select
        labelId="demo-multiple-name-label"
        id="demo-multiple-name"
        multiple
        value={names}
        onChange={handleChange}
        input={<OutlinedInput label="Name" />}
        MenuProps={MenuProps}
      >
        {buildings.map((name) => (
          <MenuItem
            key={name}
            value={name}
            style={getStyles(name, buildings, theme)}
          >
            {name}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default SelectField;
