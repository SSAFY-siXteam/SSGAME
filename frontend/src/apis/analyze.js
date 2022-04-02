import { URL, MOCK_URL } from "../commons/setting/apiConfig";
import axios from "axios";

export async function getAnalyzeGraph(header, success, fail) {
  try {
    let response = await axios.get(URL + `analysis/graph`, header);
    if (response.data.status == 200) {
      success(response);
    } else if (response.data.status !== 200) {
      throw new Error("analyzation is failed");
    }
  } catch (error) {
    fail(error);
  }
}

export async function getAnalyzeGenres(header, success, fail) {
  try {
    let response = await axios.get(URL + `analysis/genres`, header);
    if (response.data.status == 200) {
      success(response);
    } else if (response.data.status !== 200) {
      throw new Error("analyzation is failed");
    }
  } catch (error) {
    fail(error);
  }
}

export async function getAnalyzeGames(header, success, fail) {
  try {
    let response = await axios.get(URL + `analysis/games`, header);
    if (response.data.status == 200) {
      success(response);
    } else if (response.data.status !== 200) {
      throw new Error("analyzation is failed");
    }
  } catch (error) {
    fail(error);
  }
}
