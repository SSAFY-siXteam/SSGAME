import React from "react";
import LongGameCard from "./LongGameCard.js";
import gold from "../../../assets/img/medals/gold.png";

export default {
  title: "molecules/LongGameCard",
  component: LongGameCard,
};

const Template = (args) => <LongGameCard {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  info: {
    gameSeq: "1",
    gameName: "게임1",
    headerImage:
      "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
    genres: ["태그1", "태그2", "태그3"], //최대 3개
  },
};

export const Secondary = Template.bind({});
Secondary.args = {
  info: {
    gameSeq: "1",
    gameName: "게임1",
    price: "10000",
    headerImage:
      "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
    genres: ["태그1", "태그2", "태그3"], //최대 3개
  },
};
