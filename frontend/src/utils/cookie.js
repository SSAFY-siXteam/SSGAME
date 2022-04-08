import { Cookies } from "react-cookie";

const cookies = new Cookies();

export const setCookie = (name, value, option) => {
  return cookies.set(name, value, { ...option });
};

export const getCookie = (name) => {
  return cookies.get(name);
};

export const removeCookie = (name) => {
  return cookies.remove(name);
};

export const removeAllCookies = () => {
  cookies.remove("SSGAME_USER_TOKEN");
  cookies.remove("SSGAME_USER_ID");
  cookies.remove("SSGAME_USER_NO");
  cookies.remove("SSGAME_USER_SEQ");
};
