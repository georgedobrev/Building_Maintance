import axios, { AxiosResponse } from 'axios';

type Headers = { "Content-Type": string, "Authorization": string | null };
type Data = Record<string, unknown>; 
type RequestOptions = {
  method: "GET" | "POST"
  headers: Headers;
  data?: Data;

};

const get = async (url: string, token: string) => {
  const requestOptions: RequestOptions = {
    method: "GET",
    headers: { 
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json' 
    },
  };
  return handleResponse(await axios(url, requestOptions));
};

const post = async (url: string, body: any, token: string) => {
  const requestOptions: RequestOptions = {
    method: "POST",
    headers: { 
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json' 
    },
    data: body,
  };
  return handleResponse(await axios(url, requestOptions));
};

const handleResponse = async (response: AxiosResponse) => {
  if (response.status !== 200) {
    const error = (response.data && response.data.message) || response.statusText;
    throw error;
  }

  return response.data;
};

export const authWrapper = {get, post, handleResponse}
