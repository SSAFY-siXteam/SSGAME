import React, { useEffect, useState } from "react";
import {
  getAnalyzeGames,
  getAnalyzeGenres,
  getAnalyzeGraph,
  calWeight,
} from "../../../apis/analyze";
import AnalyzeGraph from "../../organisms/AnalyzeGraph/AnalyzeGraph";
import LongGameCardList from "../../organisms/LongGameCardList/LongGameCardList";
import GenreCardList from "../../organisms/GenreCardList/GenreCardList";
import AnalyzeTemplate from "../../templates/AnalyzeTemplate/AnalyzeTemplate";
import { getCookie } from "../../../utils/cookie";
import LoadingBar from "../../atoms/spinner/LoadingBar";
import HowToSetSteamTemplate from "../../templates/HowToSetSteamTemplate/HowToSetSteamTemplate";

const AnalyzePage = () => {
  const [graphData, setGraphData] = useState([]);
  const [gamesData, setGamesData] = useState([]);
  const [genresData, setGenresData] = useState([]);
  const [isSuccessToLoad, setIsSueccessToLoad] = useState(true);
  const [steamNickname, setSteamNickname] = useState("");
  const [isLoading, setIsLoading] = useState(true);
  const [noGamePlayed, setNoGamePlayed] = useState(false);
  const jwtToken = getCookie("SSGAME_USER_TOKEN");

  useEffect(() => {
    calWeight(
      {
        headers: {
          Authorization: `Bearer ` + jwtToken,
        },
      },
      () => {
        const isSuccess = true;
        getAnalyzeGraph(
          {
            headers: {
              Authorization: `Bearer ` + jwtToken,
            },
          },
          (response) => {
            console.log(response);
            setGraphData(response.data.data.categories);
            setSteamNickname(response.data.data.userNickName);
          },
          (e) => {
            // if (isSuccessToLoad) setIsSueccessToLoad(false);
          }
        );
        getAnalyzeGenres(
          {
            headers: {
              Authorization: `Bearer ` + jwtToken,
            },
          },
          (response) => {
            console.log(response);
            setGenresData(response.data.data.MostPlayedGenres);
          },
          (e) => {
            // if (isSuccessToLoad) setIsSueccessToLoad(false);
          }
        );
        getAnalyzeGames(
          {
            headers: {
              Authorization: `Bearer ` + jwtToken,
            },
          },
          (response) => {
            console.log(response);
            setGamesData(response.data.data.mostPlayedGames);
          },
          (e) => {
            // if (isSuccessToLoad) setIsSueccessToLoad(false);
          }
        );
        setIsLoading(false);
      },
      () => {
        setIsLoading(false);
        setNoGamePlayed(true);
      }
    );

    return () => {
      console.log(isSuccessToLoad);
      if (!isSuccess) setIsSueccessToLoad(false);
    };
  }, []);

  const args = {
    graph: AnalyzeGraph({ data: graphData, steamNickname: steamNickname }),
    games: LongGameCardList({
      data: gamesData,
      title: "?????? ?????? ????????? ??? ?????? top5",
    }),
    genres: GenreCardList({ data: genresData }),
  };

  console.log(genresData);

  return (
    <>
      {isLoading ? (
        <LoadingBar />
      ) : noGamePlayed ? (
        <HowToSetSteamTemplate />
      ) : (
        <div>
          <AnalyzeTemplate
            graph={args.graph}
            games={args.games}
            genres={args.genres}
          />
        </div>
      )}
    </>
  );
};

export default React.memo(AnalyzePage);
