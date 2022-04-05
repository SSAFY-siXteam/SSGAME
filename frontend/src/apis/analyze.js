import { MOCK_URL } from "../commons/setting/apiConfig";
import axios from "axios";

const URL = MOCK_URL;

export async function getAnalyzeGraph(header, success, fail) {
  axios
    .get(URL + `analyze/graph`, header)
    .then(success)
    .catch(fail);
}

export async function getAnalyzeGenres(header, success, fail) {
  axios
    .get(URL + `analyze/genres`, header)
    .then(success)
    .catch(fail);
}

export async function getAnalyzeGames(header, success, fail) {
  axios
    .get(URL + `analyze/games`, header)
    .then(success)
    .catch(fail);
}
