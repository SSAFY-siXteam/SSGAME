import React from "react";
import GenreCard from "./GenreCard.js";
import gold from "../../../assets/img/medals/gold.png";

export default {
  title: "molecules/GenreCard",
  component: GenreCard,
};

const Template = (args) => <GenreCard {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  path: gold,
  info: {
    genre: "스릴러",
    ratio: "20",
  },
};
