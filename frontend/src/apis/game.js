import axios from "axios";
import { URL, DJANGO_URL } from "../commons/setting/apiConfig";

async function getGame(header, param, success, fail) {
  try {
    let response = await axios.get(URL + `games/${param.gameSeq}`, header);
    if (response.data.status == 200) {
      success(response);
    } else if (response.data.status !== 200) {
      alert("존재하지 않는 게임입니다. 다시 확인해주세요.");
    }
  } catch (error) {
    fail(error);
  }
}
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

async function updateGameAnalysis(token, memberSeq) {
  return axios.get(DJANGO_URL + memberSeq, {
    headers: {
      Authorization: "Bearer" + token,
    },
  });
}
export { getGame, getGameList, putGameRating, updateGameAnalysis };
