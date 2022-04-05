import axios from "axios";
<<<<<<< HEAD
import { MOCK_URL, URL } from "../commons/setting/apiConfig";
<<<<<<< HEAD

=======
// const tempURL = "http://localhost:8080/api/v1";
>>>>>>> bf493856ead5940b93ba69e12c7e1a2ab66655c3
=======
import { URL, DJANGO_URL } from "../commons/setting/apiConfig";

>>>>>>> 1aa05f5181a3e56d42326c764fb68cbf9f66e1b0
async function getGame(header, param, success, fail) {
  await axios
    .get(MOCK_URL + `game/${param.gameSeq}`, header)
    .then(success)
    .catch(fail);
}
<<<<<<< HEAD

export { getGame };
=======
async function getGameList(token, param) {
  let search;
  if (param.search !== undefined) {
    search = "&search=" + param.search;
  } else {
    search = "";
  }
  console.log(param);
  return axios.get(URL + "members/games", {
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

async function putGameRating(token, data) {
  console.log(data);
  return axios.put(
    URL + "/games/" + data.gameSeq,
    { point: data.point },
    {
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
    }
  );
}
<<<<<<< HEAD
export { getGame, getGameList, putGameRating };
>>>>>>> bf493856ead5940b93ba69e12c7e1a2ab66655c3
=======

async function updateGameAnalysis(token, memberSeq) {
  console.log("start");
  return axios.get(DJANGO_URL + memberSeq, {
    headers: {
      Authorization: "Bearer" + token,
      "Content-Type": "application/json",
    },
  });
}
export { getGame, getGameList, putGameRating, updateGameAnalysis };
>>>>>>> 1aa05f5181a3e56d42326c764fb68cbf9f66e1b0
