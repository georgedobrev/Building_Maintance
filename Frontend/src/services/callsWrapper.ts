import axios, { AxiosResponse } from "axios";

type Methods = "get" | "post" | "delete";
type Headers = { "Content-Type": string; Authorization?: string };
type Data = Record<string, unknown>;
type RequestOptions = {
  method: Methods;
  headers?: Headers;
  data?: Data;
};

const get = async (url: string, token?: string) => {
  const requestOptions: RequestOptions = {
    method: "get",
    headers: token
      ? { "Content-Type": "application/json", Authorization: `Bearer ${token}` }
      : { "Content-Type": "application/json" },
  };
  return handleResponse(await axios(url, requestOptions));
};

const post = async (url: string, data: Data) => {
  const requestOptions: RequestOptions = {
    method: "post",
    headers: { "Content-Type": "application/json" },
    data,
  };
  return handleResponse(await axios(url, requestOptions));
};

const _delete = async (url: string) => {
  const requestOptions: RequestOptions = {
    method: "delete",
  };
  return handleResponse(await axios(url, requestOptions));
};

const handleResponse = (response: AxiosResponse) => response;

export const fetchWrapper = {
  get,
  post,
  delete: _delete,
};
