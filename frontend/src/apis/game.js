import axios from "axios";
import { MOCK_URL, URL } from "../commons/setting/apiConfig";
const tempURL = "http://localhost:8080/api/v1";
async function getGame(header, param, success, fail) {
  await axios
    .get(URL + `games/${param.gameSeq}`, header)
    .then(success)
    .catch(fail);
}
async function getGame1(token, param) {
  let search;
  if (param.search !== undefined) {
    search = "&search=" + param.search;
  } else {
    search = "";
  }
  console.log(param);
  return axios.get(tempURL + "/members/games", {
    params: {
      page: param.page,
      size: param.size,
      sort: param.sort,
      filter: param.filter,
      search: param.search,
    },
    headers: {
      Authorization: "Bearer " + token,
    },
  });
}
export { getGame, getGame1 };
