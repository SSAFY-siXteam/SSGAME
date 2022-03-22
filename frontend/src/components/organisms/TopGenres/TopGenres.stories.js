import React from "react";
import TopGenres from "./TopGenres.js";
import gold from "../../../assets/img/medals/gold.png";

export default {
  title: "organisms/TopGenres",
  component: TopGenres,
};

const Template = () => <TopGenres />;

export const Primary = Template.bind({});
// Primary.args = {
//   path: gold,
//   info: {
//     genre: "스릴러",
//     ratio: "20",
//   },
// };
