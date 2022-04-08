import React from "react";
import LongGameCardList from "./LongGameCardList.js";
import gold from "../../../assets/img/medals/gold.png";

export default {
  title: "organisms/LongGameCardList",
  component: LongGameCardList,
};

const Template = (args) => <LongGameCardList {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  path: gold,
  info: {
    genre: "스릴러",
    ratio: "20",
  },
};
