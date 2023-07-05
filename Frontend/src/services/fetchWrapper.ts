import axios, { AxiosResponse } from "axios";

type Methods = "get" | "post" | "delete";
type Headers = { "Content-Type": string; Authorization?: string };
type RequestOptions<T> = {
  method: Methods;
  headers?: Headers;
  data?: T;
};

const get = async (url: string, token?: string) => {
  const requestOptions: RequestOptions<undefined> = {
    method: "get",
    headers: token
      ? { "Content-Type": "application/json", Authorization: `Bearer ${token}` }
      : { "Content-Type": "application/json" },
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

// const handleResponse = (response: AxiosResponse) => {
//   if (response.status < 200 || response.status >= 300) {
//     const error = {
//       status: response.status,
//       message: (response.data && response.data.message) || response.statusText,
//     };
//     throw {
//       message: error.message,
//       response: response,
//     };
//   }
//   return response.data;
// };
const handleResponse = (response: AxiosResponse) => response;

export const fetchWrapper = {
  get,
  post,
  delete: _delete,
  handleResponse,
};
