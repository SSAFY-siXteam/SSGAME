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
      <GameInfoItem title="게임 장르" text={gameInfo.genres} isArray={true} />
      <GameInfoItem title="플랫폼" text={gameInfo.platforms} isArray={true} />
      <GameInfoItem title="웹사이트" text={gameInfo.website} />
      <GameInfoItem
        title="지원 언어"
        text={gameInfo.languages}
        isArray={true}
      />
      <GameInfoItem title="제작사" text={gameInfo.publisher} isArray={true} />
      <GameInfoItem title="개발자" text={gameInfo.developers} isArray={true} />
      {gameInfo.releaseDate !== undefined && (
        <GameInfoItem title="출시 일자" text={gameInfo.releaseDate.date} />
      )}
      <GameInfoItem title="평균 플레이 타임" text={gameInfo.averageForever} />
    </div>
  );
};

export default GameInfo;
