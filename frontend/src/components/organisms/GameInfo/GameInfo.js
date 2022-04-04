import React from "react";
import InfoText from "../../atoms/Text/InfoText/InfoText";
import Title from "../../atoms/Title/Title";
import GameInfoItem from "../../molecules/GameInfoItem/GameInfoItem";
import RatingStar from "../../atoms/RatingStart/RatingStar";

const GameInfo = ({ gameInfo }) => {
  return (
    <div>
      <Title title={gameInfo.gameName} />
      {gameInfo.isPlayed && <RatingStar />}
      <InfoText text={gameInfo.shortDescriptionKr} />
      {/* {gameInfo.isPlayed && } */}
      <GameInfoItem title="내 게임 평가" text={gameInfo.memberGameRating} />
      {/* 별점 */}

      <GameInfoItem title="게임 평점" text={gameInfo.averageRating} />
      <GameInfoItem title="게임 장르" text={gameInfo.genres + " "} />
    </div>
  );
};

export default GameInfo;
