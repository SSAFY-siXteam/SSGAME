import React from "react";
import Title from "../../atoms/Title/Title";
import MostPlayedGameItem from "../../molecules/MostPlayedGameItem/MostPlayedGameItem";
import { StyledTitle, GameItems } from "./style.js";

const MostPlayedGames = () => {
  const data = [
    {
      gameSeq: "1",
      gameName: "게임1",
      headerImage:
        "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
      genres: ["태그1", "태그2", "태그3"], //최대 3개
    },
    {
      gameSeq: "2",
      gameName: "게임2",
      headerImage:
        "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
      genres: ["태그1", "태그2", "태그3"], //최대 3개
    },
  ];
  return (
    <div>
      <StyledTitle>
        <Title title="가장 많이 플레이 한 게임 top5" />
      </StyledTitle>
      <GameItems>
        {data.map((info, index) => (
          <MostPlayedGameItem key={index} info={info} />
        ))}
      </GameItems>
    </div>
  );
};

export default MostPlayedGames;
