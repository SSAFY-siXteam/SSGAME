import React from "react";
import GameInfo from "./GameInfo";

export default {
  title: "organisms/GameInfo",
  component: GameInfo,
};

const Template = (args) => <GameInfo {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  gameInfo: {
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
  },
};
