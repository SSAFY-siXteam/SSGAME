import React from "react";
import GameCard from "./GameCard.js";

export default {
  title: "molecules/GameCard",
  component: GameCard,
};

const Template = (args) => <GameCard {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  info: {
    gameSeq: "1",
    gameName: "게임1",
    headerImage:
      "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
    genres: ["태그1", "태그2", "태그3"], //최대 3개
    price: 10000,
  },
};
