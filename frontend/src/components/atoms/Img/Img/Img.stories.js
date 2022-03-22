import React from "react";
import Img from "./Img.js";
import gold from "../../../../assets/img/medals/gold.png";

export default {
  title: "Atoms/Img",
  component: Img,
};

const Template = (args) => <Img {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  path: gold,
};
