import React, { useEffect, useState } from "react";
import {
  getAnalyzeGames,
  getAnalyzeGenres,
  getAnalyzeGraph,
} from "../../../apis/analyze";
import AnalyzeGraph from "../../organisms/AnalyzeGraph/AnalyzeGraph";
import LongGameCardList from "../../organisms/LongGameCardList/LongGameCardList";
import GenreCardList from "../../organisms/GenreCardList/GenreCardList";
import AnalyzeTemplate from "../../templates/AnalyzeTemplate/AnalyzeTemplate";
import { getCookie } from "../../../utils/cookie";

const AnalyzePage = () => {
  const [graphData, setGraphData] = useState([]);
  const [gamesData, setGamesData] = useState([]);
  const [genresData, setGenresData] = useState([]);

  const jwtToken = getCookie("SSGAME_USER_TOKEN");

  useEffect(() => {
    getAnalyzeGraph(
      {
        headers: {
          Authorization: `Bearer ` + jwtToken,
        },
      },
      (response) => {
        setGraphData(response.data.data.categories);
      },
      (e) => {
        console.log(e);
      }
    );
    getAnalyzeGenres(
      {
        headers: {
          Authorization: `Bearer ` + jwtToken,
        },
      },
      (response) => {
        setGenresData(response.data.data.mostPlayedGenres);
      },
      (e) => {
        console.log(e);
      }
    );
    getAnalyzeGames(
      {
        headers: {
          Authorization: `Bearer ` + jwtToken,
        },
      },
      (response) => {
        setGamesData(response.data.data.mostPlayedGames);
      },
      (e) => {
        console.log(e);
      }
    );
  }, []);

  const args = {
    graph: AnalyzeGraph({ data: graphData }),
    games: LongGameCardList({
      data: gamesData,
      title: "가장 많이 플레이 한 게임 top5",
    }),
    genres: GenreCardList({ data: genresData }),
  };

  return (
    <div>
      <AnalyzeTemplate
        graph={args.graph}
        games={args.games}
        genres={args.genres}
      />
    </div>
  );
};

export default AnalyzePage;
