import { useState } from "react";
import { ValidationError } from "yup";
import { FormErrors, FormValues } from "./RegisterInterfaces";
import ValidationSchema from "../pages/register/ValidationSchema";

const useAuthValidations = () => {
  const [formValues, setFormValues] = useState<FormValues>({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    building: "",
    unit: "",
    buildingName: "",
    country: "",
    region: "",
    city: "",
    district: "",
    postalCode: "",
    streetName: "",
    streetNumber: "",
    floors: "",
    entrances: "",
  });

  const [formErrors, setFormErrors] = useState<FormErrors>({});

  const setBuilding = (value: number | string) => {
    setFormValues((prevState) => ({ ...prevState, building: value }));
  };

  const setUnit = (value: string | number) => {
    const unitValue = typeof value === "string" ? parseInt(value) : value;
    setFormValues((prevState) => ({ ...prevState, unit: unitValue }));
  };

  const validateField = async (fieldName: keyof FormValues) => {
    let value = formValues[fieldName];
    try {
      await ValidationSchema.validateAt(fieldName, { [fieldName]: value });
      setFormErrors((prevState) => {
        const newState = { ...prevState };
        delete newState[fieldName];
        return newState;
      });
    } catch (error) {
      if (error instanceof ValidationError) {
        setFormErrors((prevState) => ({
          ...prevState,
          [fieldName]: (error as ValidationError).message,
        }));
      } else if (error instanceof Error) {
        console.error(error.message);
      } else {
        console.error(error);
      }
    }
  };

  return {
    formValues,
    setFormValues,
    formErrors,
    validateField,
    setBuilding,
    setUnit,
  };
};

export default useAuthValidations;
