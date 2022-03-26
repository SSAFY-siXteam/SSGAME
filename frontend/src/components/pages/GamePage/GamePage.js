import React, { useEffect } from "react";
import GameTemplate from "../../templates/GameTemplate/GameTemplate";
import Img from "../../atoms/Img/Img/Img";
import GameInfo from "../../organisms/GameInfo/GameInfo";
import Video from "../../atoms/Video/Video";

const GamePage = () => {
  const gameInfo = {
    gameSeq: "1",
    gameName: "게임1",
    headerImage:
      "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
    genres: ["action", "장르1", "장르2"], //최대 3개,
    movies:
      "http://cdn.akamai.steamstatic.com/steam/apps/912/movie480.mp4?t=1512411140",
    shortDescriptionKr: "이 게임은 정말 재밌습니다...",
    averageRating: 4.3,
    isPlayed: false,
    isRated: false,
    memberGameRating: 0,
  };

  const args = {
    img: Img({ path: gameInfo.headerImage }),
    video: Video({ path: gameInfo.movies }),
    info: GameInfo({ gameInfo: gameInfo }),
  };

  return (
    <div>
      <GameTemplate img={args.img} video={args.video} info={args.info} />
    </div>
  );
};

export default GamePage;
