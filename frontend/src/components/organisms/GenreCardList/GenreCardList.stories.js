import React from "react";
import GenreCardList from "./GenreCardList.js";

export default {
  title: "organisms/GenreCardList",
  component: GenreCardList,
};

const Template = (args) => <GenreCardList {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  data: [
    {
      genre: "장르1",
      ratio: 55,
    },
    {
      genre: "장르2",
      ratio: 40,
    },
    {
      genre: "장르3",
      ratio: 20,
    },
    {
      genre: "장르4",
      ratio: 10,
    },
    {
      genre: "장르5",
      ratio: 3,
    },
  ],
};
