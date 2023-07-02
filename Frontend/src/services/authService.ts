import { fetchWrapper } from "./fetchWrapper";
import { config } from "../config/config";
import { User } from "./loginUserInterface";

const login = async (body: User) => {
  try {
    const response = await fetchWrapper.post(
      `${config.baseURL}${config.login}`,
      body
    );
    return response;
  } catch (error) {
    throw error;
  }
};

export const authService = { login };
