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
  buildingName: yup.string().required("Building name is required"),
  unit: yup
    .mixed()
    .test(
      "is-number-or-string",
      "Unit must be a number or string",
      (value) => typeof value === "number" || typeof value === "string"
    )
    .required("Unit is required"),
  country: yup.string().required("Country is required"),
  region: yup.string().required("Region is required"),

  city: yup.string().required("City is required"),
  district: yup.string().required("District is required"),
  postalCode: yup.number().typeError("Postal code field should be a number").required("Postal code is required"),
  streetName: yup.string().required("Street name is required"),
  streetNumber: yup.number().typeError("Street Number field should be a number").required("Street number is required"),
  floors: yup.number().typeError("Floors field should be a number").required("Number of floors is required"),
  entrances: yup.number().typeError("Entrances field should be a number").required("Number of entrances is required"),
});

export default ValidationSchema;
