import * as yup from "yup";

const ValidationSchema = yup.object().shape({
  firstName: yup.string().required("First name is required"),
  lastName: yup.string().required("Last name is required"),
  username: yup.string().required("Username is required"),
  email: yup
    .string()
    .email("Enter a valid email")
    .required("Email is required"),
  password: yup
    .string()
    .required("Password is required")
    .matches(
      /^(?=.*[A-Z])(?=.*\d)[A-Za-z\d]/,
      "Password must have at least one uppercase letter and one number"
    ),
  building: yup
    .mixed()
    .test(
      "is-number-or-string",
      "Building must be a number or string",
      (value) => typeof value === "number" || typeof value === "string"
    )
    .required("Building is required"),
  unit: yup
    .mixed()
    .test(
      "is-number-or-string",
      "Unit must be a number or string",
      (value) => typeof value === "number" || typeof value === "string"
    ),
});

export default ValidationSchema;
