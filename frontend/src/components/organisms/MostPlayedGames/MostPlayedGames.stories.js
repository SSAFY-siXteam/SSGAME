import React from "react";
import MostPlayedGames from "./MostPlayedGames.js";
import gold from "../../../assets/img/medals/gold.png";

export default {
  title: "organisms/MostPlayedGames",
  component: MostPlayedGames,
};

const Template = (args) => <MostPlayedGames {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  path: gold,
  info: {
    genre: "스릴러",
    ratio: "20",
  },
};
