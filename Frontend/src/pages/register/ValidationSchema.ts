import * as yup from "yup";

const ValidationSchema = yup.object().shape({
  firstName: yup.string().required("First name is required"),
  lastName: yup.string().required("Last name is required"),
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
});

export default ValidationSchema;
