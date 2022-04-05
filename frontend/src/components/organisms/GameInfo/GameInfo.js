import React, { useState } from "react";
import InfoText from "../../atoms/Text/InfoText/InfoText";
import Title from "../../atoms/Title/Title";
import GameInfoItem from "../../molecules/GameInfoItem/GameInfoItem";
import RatingStar from "../../atoms/RatingStar/RatingStar";

const GameInfo = ({ gameInfo, onStarChange }) => {
  return (
    <div>
      <Title title={gameInfo.gameName} />
      <InfoText text={gameInfo.shortDescriptionKr} />
      {gameInfo.isPlayed && (
        <>
          <GameInfoItem title="내 게임 평가" text={gameInfo.memberGameRating} />
          <RatingStar content={{ ...gameInfo }} onStarChange={onStarChange} />
        </>
      )}
      <GameInfoItem title="게임 평점" text={gameInfo.averageRating} />
      <GameInfoItem title="게임 장르" text={gameInfo.genres + " "} />
    </div>
  );
};

export default GameInfo;
