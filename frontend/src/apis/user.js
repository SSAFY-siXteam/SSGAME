import axios from "axios";
// import { URL } from "../commons/setting/apiConfig";
//env 이관
const URL = "http://localhost:8080/api/v1";
// Spring 서버 ID 로그인
export function signIn(user) {
  return axios.post(URL + "/members" + "/login", user);
}

export function getUserInfo(id, token) {
  const header = { jwtToken: token };
  return axios.get(URL + "/members/me" + { id }, header);
}
