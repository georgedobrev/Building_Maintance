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
const getUserRole = async () => {
  const token = localStorage.getItem("token");

  if (!token) {
    throw new Error("No token found in local storage");
  }

  const response = await fetchWrapper.get(
    `${config.baseURL}${config.get_user_role}`,
    token
  );

  return response.data;
};

export const authService = { login, getUserRole };
