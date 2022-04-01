import { MOCK_URL } from "../commons/setting/apiConfig";
import axios from "axios";

const URL = MOCK_URL;

export async function getAnalyzeGraph(header, success, fail) {
  try {
    let response = await axios.get(URL + `analyze/graph`, header);
    if (response.data.status == 200) {
      success(response);
    } else if (response.data.status !== 200) {
      alert("게임 분석을 진행할 수 없습니다. 다시 시도해 주세요.");
    }
  } catch (error) {
    fail(error);
  }
}

export async function getAnalyzeGenres(header, success, fail) {
  try {
    let response = await axios.get(URL + `analyze/genres`, header);
    if (response.data.status == 200) {
      success(response);
    } else if (response.data.status !== 200) {
      alert("게임 분석을 진행할 수 없습니다. 다시 시도해 주세요.");
    }
  } catch (error) {
    fail(error);
  }
}

export async function getAnalyzeGames(header, success, fail) {
  try {
    let response = await axios.get(URL + `analyze/games`, header);
    if (response.data.status == 200) {
      success(response);
    } else if (response.data.status !== 200) {
      alert("게임 분석을 진행할 수 없습니다. 다시 시도해 주세요.");
    }
  } catch (error) {
    fail(error);
  }
}
