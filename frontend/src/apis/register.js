import axios from "axios";

//env 이관
const URL = "http://localhost:3000/api/v1";

// Spring 서버 ID 확인
export function getIdCheckAPI(id) {
  return axios.get(URL + "/members" + "/ssgameId" + "/" + id + "/exist");
}
// Spring 서버 회원가입
export function registerId(userInfo) {
  return axios.post(URL + "/members", userInfo).then((res) => console.log(res));
}
