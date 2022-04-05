import axios from "axios";
import { MOCK_URL } from "../commons/setting/apiConfig";

async function getRecommendGames(header, success, fail) {
  await axios
    .get(MOCK_URL + `/recommend`, header)
    .then(success)
    .catch(fail);
}

async function updateRecommend(memberSeq, success, fail) {
  try {
    let response = await axios.get(
      `https://j6a306.p.ssafy.io/django/${memberSeq}`
    );
    if (response.status == 200) {
      success(response);
    } else if (response.status !== 200) {
      console.log("updateRecommend is not success");
    }
  } catch (error) {
    fail(error);
  }
}

export { getRecommendGames, updateRecommend };
