import axios from "axios";
import { MOCK_URL } from "../commons/setting/apiConfig";

async function getRecommendGames(header, success, fail) {
  await axios
    .get(MOCK_URL + `/recommend`, header)
    .then(success)
    .catch(fail);
}

export { getRecommendGames };
