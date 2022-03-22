import React from "react";
import TopGenreItem from "./TopGenreItem.js";
import gold from "../../../assets/img/medals/gold.png";

export default {
  title: "molecules/TopGenreItem",
  component: TopGenreItem,
};

const Template = (args) => <TopGenreItem {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  path: gold,
  info: {
    genre: "스릴러",
    ratio: "20",
  },
};
