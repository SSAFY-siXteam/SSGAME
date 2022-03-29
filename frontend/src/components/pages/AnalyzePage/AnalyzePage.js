import React, { useEffect, useState } from "react";
import {
  getAnalyzeGames,
  getAnalyzeGenres,
  getAnalyzeGraph,
} from "../../../apis/analyze";
import AnalyzeGraph from "../../organisms/AnalyzeGraph/AnalyzeGraph";
import MostPlayedGames from "../../organisms/MostPlayedGames/MostPlayedGames";
import MostPlayedGenres from "../../organisms/MostPlayedGenres/MostPlayedGenres";
import AnalyzeTemplate from "../../templates/AnalyzeTemplate/AnalyzeTemplate";
import { getCookie } from "../../../utils/cookie";

const AnalyzePage = () => {
  const [graphData, setGraphData] = useState([]);
  const [gamesData, setGamesData] = useState([]);
  const [genresData, setGenresData] = useState([]);

  useEffect(() => {
    // jwtToken = getCookie();
    getAnalyzeGraph(
      {
        headers: {
          // Authorization: `Bearer ` + jwtToken,
        },
      },
      (response) => {
        if (response.status === 200) {
          setGraphData(response.data.data.categories);
        }
      },
      (e) => {
        console.log(e);
      }
    );
    getAnalyzeGenres(
      {
        headers: {
          // Authorization: `Bearer ` + jwtToken,
        },
      },
      (response) => {
        if (response.status === 200) {
          setGenresData(response.data.data.mostPlayedGenres);
        }
      },
      (e) => {
        console.log(e);
      }
    );
    getAnalyzeGames(
      {
        headers: {
          // Authorization: `Bearer ` + jwtToken,
        },
      },
      (response) => {
        if (response.status === 200) {
          setGamesData(response.data.data.mostPlayedGames);
        }
      },
      (e) => {
        console.log(e);
      }
    );
  }, []);

  const args = {
    graph: AnalyzeGraph({ data: graphData }),
    games: MostPlayedGames({ data: gamesData }),
    genres: MostPlayedGenres({ data: genresData }),
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
