import axios from "axios";
import { MOCK_URL, URL } from "../commons/setting/apiConfig";

async function getRecommendGames(header, success, fail) {
  console.log(header);
  await axios
    .get(URL + `recommend`, header)
    .then(success)
    .catch(fail);
}

export { getRecommendGames };
