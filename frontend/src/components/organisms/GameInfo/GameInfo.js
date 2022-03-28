import React from "react";
import InfoText from "../../atoms/Info/InfoText/InfoText";
import Title from "../../atoms/Title/Title";
import GameInfoItem from "../../molecules/GameInfoItem/GameInfoItem";

const GameInfo = ({ gameInfo }) => {
  return (
    <div>
      <Title title={gameInfo.gameName} />
      <InfoText text={gameInfo.shortDescriptionKr} />
      <GameInfoItem title="게임 평점" text={gameInfo.averageRating} />
      <GameInfoItem title="게임 장르" text={gameInfo.genres + " "} />
    </div>
  );
};

export default GameInfo;
