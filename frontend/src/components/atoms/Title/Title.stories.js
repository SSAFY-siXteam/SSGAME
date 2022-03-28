import React from "react";
import Title from "./Title.js";

export default {
  title: "Atoms/Title",
  component: Title,
};

const Template = (args) => <Title {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  primary: true,
  title: "information",
};

export const Large = Template.bind({});
Large.args = {
  size: "large",
  title: "information",
};

export const Small = Template.bind({});
Small.args = {
  size: "small",
  title: "information",
};
