import axios from "axios";
import { MOCK_URL, URL } from "../commons/setting/apiConfig";

async function getRecommendGames(header, success, fail) {
  try {
    let response = await axios.get(URL + `recommend`, header);
    if (response.data.status == 200) {
      success(response);
    } else if (response.data.status !== 200) {
      alert("게임 목록을 가져올 수 없습니다. 다시 시도해 주세요.");
    }
  } catch (error) {
    fail(error);
  }
}

async function updateRecommend(memberSeq, success, fail) {
  try {
    let response = await axios.get(
      `https://j6a306.p.ssafy.io/django/${memberSeq}`
    );
    if (response.data.status == 200) {
      success(response);
    } else if (response.data.status !== 200) {
      alert("게임 목록을 가져올 수 없습니다. 다시 시도해 주세요.");
    }
  } catch (error) {
    fail(error);
  }
}

export { getRecommendGames, updateRecommend };
