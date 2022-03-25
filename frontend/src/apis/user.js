import axios from "axios";

//env 이관
const URL = "http://localhost:3000/api/v1";

// Spring 서버 ID 로그인
export function signIn(user) {
  return axios.post(URL + "/members" + "/login", user);
}
