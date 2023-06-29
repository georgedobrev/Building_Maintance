import { useState } from "react";
import { ValidationError } from "yup";
import { FormErrors, FormValues } from "../pages/register/RegisterInterfaces";
import ValidationSchema from "../pages/register/ValidationSchema";

const useAuthValidations = () => {
  const [formValues, setFormValues] = useState<FormValues>({
    firstName: "",
    lastName: "",
    username: "",
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
  const setBuilding = (value: string | number) => {
    setFormValues((prevState) => ({ ...prevState, building: value }));
  };

  const setUnit = (value: string | number) => {
    setFormValues((prevState) => ({ ...prevState, unit: value }));
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
