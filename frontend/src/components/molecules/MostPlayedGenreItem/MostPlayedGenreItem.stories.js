import React from "react";
import MostPlayedGenreItem from "./MostPlayedGenreItem.js";
import gold from "../../../assets/img/medals/gold.png";

export default {
  title: "molecules/MostPlayedGenreItem",
  component: MostPlayedGenreItem,
};

const Template = (args) => <MostPlayedGenreItem {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  path: gold,
  info: {
    genre: "스릴러",
    ratio: "20",
  },
};
