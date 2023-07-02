import axios, { AxiosResponse } from "axios";

type Methods = "get" | "post" | "delete";
type Headers = { "Content-Type": string };
type Data = Record<string, unknown>;
type RequestOptions = {
  method: Methods;
  headers?: Headers;
  data?: Data;
};

const get = async (url: string) => {
  const requestOptions: RequestOptions = {
    method: "get",
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

const handleResponse = async (response: AxiosResponse) => {
  if (response.status !== 200) {
    const error =
      (response.data && response.data.message) || response.statusText;
    throw error;
  }

  return response.data;
};

export const fetchWrapper = {
  get,
  post,
  delete: _delete,
  handleResponse,
};
