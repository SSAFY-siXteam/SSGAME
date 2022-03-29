import axios from "axios";
import { MOCK_URL, URL } from "../commons/setting/apiConfig";

async function getGame(header, param, success, fail) {
  await axios
    .get(URL + `game/${param.gameSeq}`, header)
    .then(success)
    .catch(fail);
}

export { getGame };
