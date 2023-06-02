import { useState } from "react";
import { FormErrors, FormValues } from "../pages/register/RegisterInterfaces";
import ValidationSchema from "../pages/register/ValidationSchema";
import { ValidationError } from "yup";

 const useAuthValidations = () => {

  const [formValues, setFormValues] = useState<FormValues>({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  });
  
  const [formErrors, setFormErrors] = useState<FormErrors>({});

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

  return { formValues, setFormValues, formErrors, validateField };
};


export default useAuthValidations;