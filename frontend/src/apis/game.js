import axios from "axios";
import { MOCK_URL } from "../commons/setting/apiConfig";

async function getGame(header, param, success, fail) {
  await axios
    .get(MOCK_URL + `game/${param.gameseq}`, header)
    .then(success)
    .catch(fail);
}

export { getGame };
