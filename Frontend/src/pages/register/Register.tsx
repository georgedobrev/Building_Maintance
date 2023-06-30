import React, { ChangeEvent, useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import {
  Box,
  Button,
  TextField,
  Typography,
  useTheme,
  useMediaQuery,
  Autocomplete,
} from "@mui/material";
import SelectField from "./SelectField";
import useAuthValidations from "../../common/utils";
import { FormValues } from "./RegisterInterfaces";
import { addUser } from "../../store/users/userSlice";
import Users from "../users/Users.json";

const REQUIRED_FIELDS: (keyof FormValues)[] = [
  "firstName",
  "lastName",
  "email",
  "building",
  "unit",
];

const Register: React.FC<FormValues> = () => {
  const theme = useTheme();
  const buildings = Array.from(new Set(Users.map((user) => user.buildingID)));
  const units = Array.from(new Set(Users.map((user) => user.unitID)));

  const {
    formValues,
    setFormValues,
    formErrors,
    validateField,
    setBuilding,
    setUnit,
  } = useAuthValidations();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    Object.keys(formValues).forEach((field) =>
      validateField(field as keyof FormValues)
    );

    const userValues = Object.fromEntries(
      REQUIRED_FIELDS.map((field) => [field, formValues[field]])
    );

    const randomIdGenerator = Math.random();

    const newUser = {
      ...userValues,
      id: randomIdGenerator,
      unitID: formValues.unit,
    };

    dispatch(addUser(newUser));
    navigate("/users");
  };

  const areAllFieldsFilled = (
    requiredFields: (keyof FormValues)[]
  ): boolean => {
    return requiredFields.every((field) => formValues[field] !== "");
  };

  const handleChange = (
    fieldName: keyof FormValues,
    event: ChangeEvent<HTMLInputElement>
  ) => {
    setFormValues({
      ...formValues,
      [fieldName]: event.target.value,
    });
  };

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      height="100vh"
      bgcolor={theme.palette.background.default}
      overflow="auto"
    >
      <Typography
        variant="h4"
        component="h1"
        gutterBottom
        sx={{
          mt: "8%",
          fontFamily: theme.typography.fontFamily,
          fontWeight: 700,
          color: theme.palette.primary.main,
          textDecoration: "none",
        }}
      >
        Register User
      </Typography>
      <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{
          mt: "1%",
          height: "100vh",
          width: "40vw",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: 3,
        }}
      >
        <TextField
          placeholder="First Name"
          variant="outlined"
          sx={{ width: "100%" }}
          value={formValues.firstName}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            handleChange("firstName", e)
          }
          onBlur={() => validateField("firstName")}
          error={!!formErrors.firstName}
          helperText={formErrors.firstName}
        />
        <TextField
          placeholder="Last Name"
          variant="outlined"
          sx={{ width: "100%" }}
          value={formValues.lastName}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            handleChange("lastName", e)
          }
          onBlur={() => validateField("lastName")}
          error={!!formErrors.lastName}
          helperText={formErrors.lastName}
        />
        <TextField
          placeholder="Email"
          variant="outlined"
          sx={{ width: "100%" }}
          value={formValues.email}
          onChange={(e: ChangeEvent<HTMLInputElement>) =>
            handleChange("email", e)
          }
          onBlur={() => validateField("email")}
          error={!!formErrors.email}
          helperText={formErrors.email}
        />
        <Autocomplete
          id="combo-box-demo"
          options={buildings}
          sx={{ width: "100%" }}
          getOptionLabel={(option) => option}
          renderInput={(params) => (
            <TextField {...params} label="Building" margin="normal" />
          )}
          value={formValues.building}
          onChange={(event, newValue) =>
            setBuilding(newValue?.toString() || "")
          }
        />
        <SelectField
          label="Unit"
          items={units}
          value={formValues.unit}
          onChange={setUnit}
        />
        <Button
          type="submit"
          variant="contained"
          color="primary"
          sx={{ width: "100%" }}
          disabled={!areAllFieldsFilled(REQUIRED_FIELDS)}
        >
          Register
        </Button>
      </Box>
    </Box>
  );
};

export default Register;
