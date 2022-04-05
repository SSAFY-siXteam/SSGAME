import axios from "axios";
// import { URL } from "../commons/setting/apiConfig";
//env 이관
<<<<<<< HEAD
const URL = "http://localhost:8080/api/v1";
=======
// const URL = "http://localhost:8080/api/v1/";

>>>>>>> bf493856ead5940b93ba69e12c7e1a2ab66655c3
// Spring 서버 ID 로그인
export function signIn(user) {
  return axios.post(URL + "/members" + "/login", user);
}

export function getUserInfo(token) {
  console.log(typeof token);
<<<<<<< HEAD
  return axios.get(URL + "/members/me", {
=======
  return axios.get(URL + "members/me", {
    headers: {
      Authorization: "Bearer " + token,
    },
  });
}
export function putUserInfo(token, data) {
  return axios.put(URL + "members", data, {
>>>>>>> bf493856ead5940b93ba69e12c7e1a2ab66655c3
    headers: {
      Authorization: "Bearer " + token,
    },
  });
}
