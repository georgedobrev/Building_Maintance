import { fetchWrapper } from "./fetchWrapper";
import { config } from "../config/config";
import { User } from "./loginUserInterface";

const login = async (body: User) => {
  try {
    const response = await fetchWrapper.post<User>(
      `${config.baseURL}${config.login}`,
      body
    );
    return response;
  } catch (error) {
    throw error;
  }
};
const getUserRole = async (token: string) => {
  const response = await fetchWrapper.get(
    `${config.baseURL}${config.get_user_role}`,
    token
  );
  return response.data;
};

export const authService = { login, getUserRole };
