import axios, { AxiosResponse } from "axios";

type Methods = "get" | "post" | "delete";
type Headers = { "Content-Type": string };
type RequestOptions<T> = {
  method: Methods;
  headers?: Headers;
  data?: T;
};

const get = async (url: string) => {
  const requestOptions: RequestOptions<undefined> = {
    method: "get",
  };
  return handleResponse(await axios(url, requestOptions));
};

const post = async <T>(url: string, data: T) => {
  const requestOptions: RequestOptions<T> = {
    method: "post",
    headers: { "Content-Type": "application/json" },
    data,
  };
  return handleResponse(await axios(url, requestOptions));
};

const _delete = async (url: string) => {
  const requestOptions: RequestOptions<undefined> = {
    method: "delete",
  };
  return handleResponse(await axios(url, requestOptions));
};

const handleResponse = (response: AxiosResponse) => {
  if (response.status !== 200) {
    const error = {
      status: response.status,
      message: (response.data && response.data.message) || response.statusText,
    };
    throw {
      message: error.message,
      response: response,
    };
  }

  return response.data;
};

export const fetchWrapper = {
  get,
  post,
  delete: _delete,
  handleResponse,
};
