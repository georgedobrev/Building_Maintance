import axios, { AxiosResponse } from "axios";
import { config } from "../config/config";
import { fetchWrapper } from "./callsWrapper";

type Headers = { "Content-Type": string; Authorization: string | null };
type Data = Record<string, unknown>;
type RequestOptions = {
  method: "GET" | "POST";
  headers: Headers;
  data?: Data;
};

const get = async (url: string, token: string) => {
  const requestOptions: RequestOptions = {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  };
  return handleResponse(await axios(url, requestOptions));
};

const post = async (url: string, body: any, token: string) => {
  const requestOptions: RequestOptions = {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
    data: body,
  };
  return handleResponse(await axios(url, requestOptions));
};

const login = async (body: any) => {
  const response = await fetchWrapper.post(
    `${config.baseURL}${config.login}`,
    body
  );

  if (response.status >= 200 && response.status < 300) {
    return { status: response.status, data: response.data };
  } else {
    throw { status: response.status, data: response.data };
  }
};

const getUserRole = async (token: string) => {
  const response = await fetchWrapper.get(
    `${config.baseURL}${config.get_user_role}`,
    token
  );
  return response.data;
};

const handleResponse = async (response: AxiosResponse) => {
  if (response.status !== 200) {
    const error =
      (response.data && response.data.message) || response.statusText;
    throw error;
  }

  return response.data;
};

export const authService = { get, post, login, handleResponse, getUserRole };
